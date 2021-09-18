package com.zhoult.mongodbspringbootdemo.common.entity;

import lombok.Data;

/**
 * @author 面如冠玉、出类拔萃
 * @version 1.0
 * @date 2021/9/13 下午4:55
 */
@Data
public class RequestSearch {
    Integer pageNo;
    String searchKeywords;
    Integer pageSize;
}
