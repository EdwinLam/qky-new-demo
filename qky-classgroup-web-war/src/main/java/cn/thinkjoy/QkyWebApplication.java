package cn.thinkjoy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//启用缓存
@EnableCaching
//启用异步
@EnableAsync
public class QkyWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(QkyWebApplication.class, args);
    }
}
