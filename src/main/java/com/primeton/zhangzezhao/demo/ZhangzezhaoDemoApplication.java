package com.primeton.zhangzezhao.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(value="com.primeton.zhangzezhao.demo.dao")
@EnableSwagger2
public class ZhangzezhaoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhangzezhaoDemoApplication.class, args);
	}
}
