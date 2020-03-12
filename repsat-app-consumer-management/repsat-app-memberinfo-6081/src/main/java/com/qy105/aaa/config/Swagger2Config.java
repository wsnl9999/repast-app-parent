package com.qy105.aaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/11 18:21
 * @description：
 * @modified By：
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) // 描述该网站的信息
                .select() // 查询对外所要提供的接口都是什么(consumer项目的Controller)
                .apis(RequestHandlerSelectors.basePackage("com.qy105.aaa.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("订单项目 服务接口")
                .description("提供了项目中所有接口信息")
                .contact(new Contact("Ws","www.4933.com","2222222@qq.com"))
                .version("1.0 beta")
                .build();
    }
}
