package com.mayikt;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/3/27 0027 下午 5:17
 * @version: V1.0
 */
@EnableFeignClients
@EnableSwagger2Doc
@SpringBootApplication
@MapperScan(basePackages = "com.mayikt.member.mapper")
@EnableAsync
public class MemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }

}

