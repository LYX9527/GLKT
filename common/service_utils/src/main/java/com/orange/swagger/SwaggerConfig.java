package com.orange.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : GLKT
 * @package : com.orange.swagger
 * @className : SwaggerConfig
 * @description:
 * @date : 2022/7/1 12:54
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    // swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //.paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();
    }
    // 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("接口文档")
                // 创建人信息
                .contact(new Contact("LYX",  "https://github.com/LYX9527",  "lyxing9527@163.com"))
                // 版本号
                .version("V1.0")
                // 描述
                .description("接口")
                .build();
    }
}
