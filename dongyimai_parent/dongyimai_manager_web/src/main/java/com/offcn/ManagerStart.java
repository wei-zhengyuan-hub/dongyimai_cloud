package com.offcn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wzy
 * @version 0.0.3
 * @description ManagerStart
 * @since 2020/12/5 16:06
 */
@SpringCloudApplication//@SpringBootApplication @EnableDiscoveryClient @EnableCircuitBreaker
@MapperScan("com.offcn.mapper")
public class ManagerStart {
    public static void main(String[] args) {

        SpringApplication.run(ManagerStart.class);

    }
}
