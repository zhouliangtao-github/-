package com.zhoult.mongodbspringbootdemo.common.domain.googleSyncHTMLPojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author 面如冠玉、出类拔萃
 * @version 1.0
 * @date 2021/9/13 下午12:35
 */
@Data
@AllArgsConstructor
@Document("data_SearchResult")
public class SearchResult {
    private String docID;
    private String url;
    private String title;
    private String abst;
    private String website_name;
    private String img_url;
    private String relate_info;
    private Long pub_time;
    private String domain;
    private Integer total;
}
