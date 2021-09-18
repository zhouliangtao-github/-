package com.zhoult.mongodbspringbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAsync //开启异步方法
@EnableScheduling //开启定时任务
@EnableSwagger2
@SpringBootApplication/*(exclude = {RestClientAutoConfiguration.class, MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})*/
@ComponentScan(basePackages = "com.zhoult")
public class MongodbSpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbSpringbootDemoApplication.class, args);
    }

}
