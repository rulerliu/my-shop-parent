package com.mayikt.weixin;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/3/27 0027 下午 4:59
 * @version: V1.0
 */
@SpringBootApplication
@EnableSwagger2Doc
@MapperScan(basePackages = "com.mayikt.weixin.mapper")
public class WeiXinApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeiXinApplication.class, args);
    }

}
