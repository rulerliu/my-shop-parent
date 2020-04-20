package com.mayikt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/4/20 0020 下午 2:29
 * @version: V1.0
 */
@SpringBootApplication
@MapperScan("com.mayikt.job.mapper")
public class MemberJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberJobApplication.class);
    }

}
