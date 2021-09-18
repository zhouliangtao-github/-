package com.zhoult.mongodbspringbootdemo.serviceutil.impl;

import com.mongodb.client.FindIterable;
import com.zhoult.mongodbspringbootdemo.common.domain.googleSyncHTMLPojo.LangType;
import com.zhoult.mongodbspringbootdemo.serviceutil.ToGetKeywords;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 面如冠玉、出类拔萃
 * @version 1.0
 * @date 2021/9/14 上午11:05
 */
@Service
@Slf4j
public class ToGetKeywordsImpl implements ToGetKeywords {
    @Autowired
    @Qualifier("testMongoTemplate")
    private MongoTemplate testMongoTemplate;
    @Autowired
    @Qualifier("crawlerMongoTemplate")
    private MongoTemplate crawlerMongoTemplate;
    @Autowired
    private OnlineSearchServiceIml onlineSearchServiceIml;
    public static final String COLLECTION_NAME = "forGetKeywords";
    List<String> keywordsList = new ArrayList<>();

    @Override
    public void mainTask() {

        FindIterable<Document> documents = testMongoTemplate.getCollection("forGetKeywords").find();
        for (Document document : documents) {
            String keywords = (String) document.getString("keywords");
            keywordsList.add(keywords);
        }
        if (keywordsList == null) {
            log.info("*********没有查到任何keywords***************");
            System.out.println("********没有查到任何keywords******");
            return;
        }
        for (String searchKeywords : keywordsList) {
            onlineSearchServiceIml.searchByKeywordsAndLang(1, searchKeywords, LangType.zh, 100);
        }
    }
}
