package com.offcn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wzy
 * @version 0.0.3
 * @description ShopStart
 * @since 2020/12/5 16:18
 */
//@SpringBootApplication
//@EnableDiscoveryClient
@SpringCloudApplication
public class ShopStart {

    public static void main(String[] args) {

        SpringApplication.run(ShopStart.class);

    }

}
