package com.zhoult.mongodbspringbootdemo.test;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 面如冠玉、出类拔萃
 * @version 1.0
 * @date 2021/9/10 下午5:32
 */
@RestController
public class TestMongo {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public TestMongo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping(value = "/test")
    public void testMongoConnect(){
        MongoCollection<Document> project_warehousing_info = mongoTemplate.getCollection("project_warehousing_info");
        long l = project_warehousing_info.countDocuments();
        System.out.println(l);
    }
}
