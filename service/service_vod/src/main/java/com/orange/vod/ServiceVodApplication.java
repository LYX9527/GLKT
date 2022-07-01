package com.orange.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : GLKT
 * @package : PACKAGE_NAME
 * @className : com.orange.vod.ServiceVodApplication
 * @description:
 * @date : 2022/7/1 11:20
 */
@SpringBootApplication
@ComponentScan("com.orange")
public class ServiceVodApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVodApplication.class, args);
    }
}
