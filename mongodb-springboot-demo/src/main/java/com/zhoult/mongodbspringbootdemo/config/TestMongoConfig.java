package com.zhoult.mongodbspringbootdemo.config;

import com.mongodb.MongoClientURI;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.zhoult.mongodbspringbootdemo", mongoTemplateRef = "testMongoTemplate")
public class TestMongoConfig {
    @Bean
    @ConfigurationProperties(prefix="spring.data.mongodb.test")
    public MongoProperties testMongoProperties() {
        return new MongoProperties();
    }

    @Bean(name = "testMongoTemplate")
    public MongoTemplate testMongoTemplate() throws Exception {
        return new MongoTemplate(testFactory(testMongoProperties()));
    }

    @Bean
    public MongoDbFactory testFactory(MongoProperties mongoProperties) throws Exception {
        final MongoClientURI mongoClientURI = new MongoClientURI(mongoProperties.getUri());
        return new SimpleMongoDbFactory(mongoClientURI);
    }
}
