package club.superk.hueacm.backend.controller.oss;

import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.oss.OSSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/oss")
public class OSSController {

    @Autowired
    private OSSService ossService;

    @PostMapping("/upload")
    public Result<String> fileLoad(@RequestParam("file") MultipartFile multipartFile) throws FileNotFoundException {
        return Result.success(ossService.uploadFile(multipartFile));
    }

}
