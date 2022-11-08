package club.superk.hueacm.coderunningsystem;

import club.superk.hueacm.coderunningsystem.service.impl.BotRunningServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodeRunningSystem {
    public static void main(String[] args) {
        BotRunningServiceImpl.botPool.start();
        SpringApplication.run(CodeRunningSystem.class, args);
    }
}
