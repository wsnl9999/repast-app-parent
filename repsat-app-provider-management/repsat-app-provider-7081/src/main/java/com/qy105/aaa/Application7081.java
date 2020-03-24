package com.qy105.aaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/11 18:38
 * @description：
 * @modified By：
 */
@SpringBootApplication
@MapperScan("com.qy105.aaa.mapper")
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableScheduling
/*
@EnableTransactionManagement
*/
public class Application7081 {
    public static void main(String[] args) {
        SpringApplication.run(Application7081.class,args);
    }
}
