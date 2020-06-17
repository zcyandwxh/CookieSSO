package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author huixing
 * @description
 * @date 2020/6/16
 */
@SpringBootApplication
public class VipApp {
    public static void main(String[] args) {
        SpringApplication.run(VipApp.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
