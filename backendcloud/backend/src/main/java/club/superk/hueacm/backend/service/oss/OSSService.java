package club.superk.hueacm.backend.service.oss;

import club.superk.hueacm.backend.result.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.Map;

public interface OSSService {
    Result<Map<String, String>> policy();

    String uploadFile(MultipartFile file) throws FileNotFoundException;

}
