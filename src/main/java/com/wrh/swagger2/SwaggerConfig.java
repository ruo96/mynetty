package com.wrh.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author wuruohong
 * @Classname SwaggerConfig
 * @Description TODO
 * @Date 2020/12/17 15:53
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wrh.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                .description("mynetty 测试文档")
                .contact(new Contact("wrh","https://www.baidu.com","ww-1096@163.com"))
                .version("v1.0")
                .title("接口测试文档")
                .license("my api")
                .licenseUrl("http://www.baidu.com")
                .build());
    }
}
