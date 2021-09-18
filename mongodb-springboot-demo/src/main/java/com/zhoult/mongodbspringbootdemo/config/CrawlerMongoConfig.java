package com.zhoult.mongodbspringbootdemo.config;

import com.mongodb.MongoClientURI;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
//@EnableMongoRepositories(basePackages = "com.zhoult.mongodbspringbootdemo", mongoTemplateRef = "crawlerMongoTemplate")
public class CrawlerMongoConfig {
    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.data.mongodb.crawler")
    public MongoProperties crawlerMongoProperties() {
        return new MongoProperties();
    }

    @Primary
    @Bean(name = "crawlerMongoTemplate")
    public MongoTemplate crawlerMongoTemplate() throws Exception {
        return new MongoTemplate(crawlerFactory(crawlerMongoProperties()));
    }

    @Bean
    @Primary
    public MongoDbFactory crawlerFactory(MongoProperties mongoProperties) throws Exception {
        final MongoClientURI mongoClientURI = new MongoClientURI(mongoProperties.getUri());
        return new SimpleMongoDbFactory(mongoClientURI);
    }
}
