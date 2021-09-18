package com.zhoult.mongodbspringbootdemo.config;

/*import com.mongodb.MongoClientURI;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

*//**
 * @author 面如冠玉、出类拔萃
 * @version 1.0
 * @date 2021/9/10 下午5:42
 *//*
@Configuration
@EnableMongoRepositories(basePackages = "com.zhoult.mongodbspringbootdemo", mongoTemplateRef = "testMongoTemplate")
public class MongodbConfig {
    @Bean
    @ConfigurationProperties(prefix="spring.data.mongodb.test")
    public MongoProperties testMongoProperties() {
        return new MongoProperties();
    }

    @Bean
    public MongoDbFactory testFactory(MongoProperties mongoProperties) throws Exception {
        final MongoClientURI mongoClientURI = new MongoClientURI(mongoProperties.getUri());
        return new SimpleMongoDbFactory(mongoClientURI);
    }
//    @Scope(value = "prototype")
    @Bean(name = "testMongoTemplate")
    public MongoTemplate testMongoTemplate() throws Exception {
        return new MongoTemplate(testFactory(testMongoProperties()));
    }
}*/
