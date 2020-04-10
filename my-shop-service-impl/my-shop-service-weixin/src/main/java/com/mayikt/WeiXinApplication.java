package com.mayikt;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/3/27 0027 下午 4:59
 * @version: V1.0
 */
@SpringBootApplication
@EnableSwagger2Doc
@EnableFeignClients
@MapperScan(basePackages = "com.mayikt.weixin.mapper")
public class WeiXinApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeiXinApplication.class, args);
    }

}
