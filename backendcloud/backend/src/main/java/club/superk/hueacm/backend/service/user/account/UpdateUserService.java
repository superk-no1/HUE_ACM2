package club.superk.hueacm.backend.service.user.account;

import club.superk.hueacm.backend.result.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.Map;

public interface UpdateUserService {

    Result<Void> updateUserInfo(Map<String, String> data);

    Result<String> updateUserAvatar(MultipartFile file) throws FileNotFoundException;

}
