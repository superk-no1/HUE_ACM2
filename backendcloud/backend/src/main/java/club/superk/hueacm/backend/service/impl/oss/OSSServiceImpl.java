package club.superk.hueacm.backend.service.impl.oss;

import club.superk.hueacm.backend.mapper.UserMapper;
import club.superk.hueacm.backend.pojo.User;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.impl.util.UserDetailsImpl;
import club.superk.hueacm.backend.service.oss.OSSService;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class OSSServiceImpl implements OSSService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<Map<String, String>> policy() {
        // 阿里云秘钥
        String accessId = "LTAI5tGZ5YghN2Su19km9Nyi";
        String accessKey = "tYXhenZNLXYnf71zdmGwSPW1luGmwA";
        // 地域节点
        String endpoint = "oss-cn-guangzhou.aliyuncs.com";
        // Bucket名称
        String bucket = "superk-bucket";
        // 填写Host地址，格式为https://bucketname.endpoint。
        String host = "https://" + bucket + "." + endpoint;

        // 设置上传回调URL，即回调服务器地址，用于处理应用服务器与OSS之间的通信。OSS会在文件上传完成后，把文件上传信息通过此回调URL发送给应用服务器。
        // String callbackUrl = "https://192.168.0.0:8888";

        // 设置上传到OSS文件的前缀，可置空此项。置空后，文件将上传至Bucket的根目录下。
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());

        String dir = "SuperK/" + date + "/";

        // 创建ossClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessId, accessKey);
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            Map<String, String> respMap = new LinkedHashMap<String, String>();
            respMap.put("accessId", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            // respMap.put("expire", formatISO8601Date(expiration));
            return Result.success(respMap);

//            JSONObject jasonCallback = new JSONObject();
//            jasonCallback.put("callbackUrl", callbackUrl);
//            jasonCallback.put("callbackBody",
//                    "filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
//            jasonCallback.put("callbackBodyType", "application/x-www-form-urlencoded");
//            String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
//            respMap.put("callback", base64CallbackBody);
//
//            JSONObject ja1 = JSONObject.fromObject(respMap);
//            // System.out.println(ja1.toString());
//            response.setHeader("Access-Control-Allow-Origin", "*");
//            response.setHeader("Access-Control-Allow-Methods", "GET, POST");
//            response(request, response, ja1.toString());

        } catch (Exception e) {
            // Assert.fail(e.getMessage());
            System.out.println(e.getMessage());
        } finally {
            ossClient.shutdown();
        }
        return null;
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        String endpoint = "oss-cn-guangzhou.aliyuncs.com";
        String accessKeyId = "LTAI5tGZ5YghN2Su19km9Nyi";
        String accessKeySecret = "tYXhenZNLXYnf71zdmGwSPW1luGmwA";
        String bucketName = "superk-bucket";

        // 创建OSSClient实例。
        OSS ossClient = null;

        try {
            // 创建 OSS 实例
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 获取文件上传的流
            InputStream inputStream = multipartFile.getInputStream();
            // 构建目录
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String datePath = simpleDateFormat.format(new Date());
            // 获取文件名
            String originName = multipartFile.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            String suffix = originName.substring(originName.lastIndexOf("."));
            String newName = fileName + suffix;
            String fileUrl = "avatar/" + datePath + "/" + newName;
            // 文件上传到阿里云服务器
            ossClient.putObject(bucketName, fileUrl, inputStream);

            // 更新用户头像
            String avatarUrl = "https://" + bucketName + "." + endpoint + "/" + fileUrl;
            user.setAvatar(avatarUrl);
            userMapper.updateById(user);
            return avatarUrl;
        } catch (Exception oe) {
            oe.printStackTrace();
            return "upload failed!";
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
