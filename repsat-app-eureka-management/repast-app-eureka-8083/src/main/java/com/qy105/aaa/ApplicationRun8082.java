package com.qy105.aaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/9 20:20
 * @Description
 **/
@SpringBootApplication
@EnableEurekaServer
class ApplicationRun8083 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun8083.class, args);
    }

}
