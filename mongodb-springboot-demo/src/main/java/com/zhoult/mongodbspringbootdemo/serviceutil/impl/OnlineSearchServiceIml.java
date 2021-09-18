package com.zhoult.mongodbspringbootdemo.serviceutil.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.MongoCursor;
import com.singhand.sysdev.utils.log4wk.KLog;
import com.singhand.sysdev.utils.tools.ExceptionUtils;
import com.zhoult.mongodbspringbootdemo.common.domain.googleSyncHTMLPojo.SearchResult;
import com.zhoult.mongodbspringbootdemo.common.entity.CodeMessageConstant;
import com.zhoult.mongodbspringbootdemo.common.entity.CommonResult;
import com.zhoult.mongodbspringbootdemo.serviceutil.MongoUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 面如冠玉、出类拔萃
 * @version 1.0
 * @date 2021/9/13 上午11:51
 */
@Slf4j
@Component
public class OnlineSearchServiceIml {
    @Autowired
    MongoConverter mongoConverter;

    int pageNo = 1;
    int pageSize = 10;
    private SearchResult searchResult;
    @Autowired
    MongoUtils mongoUtils;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    @Qualifier("testMongoTemplate")
    private MongoTemplate testMongoTemplate;


    @Value("${apiGoogleSyncSearchTask.endpoint:http://node1-calcsvr.realsvr.cs-xc-idc2-area1.singhand.com:9090/userTaskController/createGoogleSyncSearchHtmlTask}")
    private String htmlGoogleSyncSearchTask;
    /**
     * @param pageNo 找第几页
     * @param searchKeywords 搜索关键词
     * @param langType  en , zh
     * @param pageSize  一页弄多少条
     * @return
     */
    public CommonResult<List<SearchResult>> searchByKeywordsAndLang (int pageNo, String searchKeywords, Enum langType, int pageSize) {
        List<SearchResult> listSearchResult = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject params = new JSONObject();
        params.put("pageNo", pageNo);
        params.put("searchKeywords", searchKeywords);
        params.put("langType", langType);
        params.put("pageSize", pageSize);

        HttpEntity<JSONObject> request = new HttpEntity(params, headers);
        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(htmlGoogleSyncSearchTask, request, JSONObject.class);
        JSONObject result = responseEntity.getBody();

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            JSONArray jsonArray = result.getJSONArray("data");
            for (int i = 0; i < jsonArray.size(); i++) {
                String docID = jsonArray.getJSONObject(i).getString("docID");
                String url = jsonArray.getJSONObject(i).getString("url");
                String title = jsonArray.getJSONObject(i).getString("title");
                String abst = jsonArray.getJSONObject(i).getString("abst");
                String website_name = jsonArray.getJSONObject(i).getString("website_name");
                String img_url = jsonArray.getJSONObject(i).getString("img_url");
                String relate_info = jsonArray.getJSONObject(i).getString("relate_info");
                Long pub_time = jsonArray.getJSONObject(i).getLong("pub_time");
                String domain = jsonArray.getJSONObject(i).getString("domain");
                Integer total = jsonArray.getJSONObject(i).getInteger("total");
                searchResult = new SearchResult(
                        docID,
                        url,
                        title,
                        abst,
                        website_name,
                        img_url,
                        relate_info,
                        pub_time,
                        domain,
                        total
                );
                try {
//                    String testDomain = simplifyEnMean(searchResult.getUrl());
//                    Boolean aBoolean = checkDomainRepeat(searchResult);
                    if (checkDomainRepeat(searchResult)==true) {
                        System.out.println("已纯在");
                    } else {
                        System.out.println("不纯在");
                        listSearchResult.add(searchResult);
//                        testMongoTemplate.insert(searchResult);
//  把域名插入第二个表中
                        Document document2 = new Document();
                        document2.put("domain", simplifyEnMean(searchResult.getUrl()));
                        document2.put("url", url);
                        testMongoTemplate.getCollection("mydomain").insertOne(document2);
                        System.out.println("mongoDB插入成功");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //for
            }
            return new CommonResult<>(1, CodeMessageConstant.REQUEST_SUCCESS, listSearchResult);
        } else {
            System.out.println("爬虫给的数据获取失败或没有");
            log.info("*********爬虫给的数据获取失败或没有***************" );
            return new CommonResult<>(0, CodeMessageConstant.REQUEST_FAILED, null);
        }
    }

    /**
     * url-->域名domain
     * @param url
     * @return
     */
    public static String simplifyEnMean(String url) {
        String res = StringUtils.EMPTY;
        if (StringUtils.isBlank(url)) {
            return StringUtils.EMPTY;
        }
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        try {
            res = new URL(url).getHost();
        } catch (Exception e) {
            KLog.error("getHost", "getHost Err", url, ExceptionUtils.errInfo(e));
        }
        return res;
    }

    /**
     * 判断字段是否存在
     */
    public Boolean checkDomainRepeat(SearchResult searchResult) throws Exception {
        if (searchResult == null || searchResult.getUrl() == null || searchResult.getUrl().equals("")) {
            return false;
        }
        String domain = simplifyEnMean(searchResult.getUrl());
        MongoCursor<Document> cursor = mongoUtils.queryMongoBidDataSources(domain);
        int count = 0;
        while (cursor.hasNext()) {
            cursor.next();
            count++;
        }
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
}
