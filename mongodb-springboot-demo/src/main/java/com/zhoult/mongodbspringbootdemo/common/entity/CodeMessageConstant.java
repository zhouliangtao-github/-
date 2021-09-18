package com.zhoult.mongodbspringbootdemo.common.entity;

import lombok.Data;

/**
 * @author 面如冠玉、出类拔萃
 * @version 1.0
 * @date 2021/9/13 上午10:07
 */
@Data
public class CodeMessageConstant {
    /**
     * code部分
     */
    public static final int FAILED = 0;
    public static final int SUCCESS =1;

    /**
     * message部分
     */
    public static final String REQUEST_SUCCESS="request success";
    public static final String REQUEST_FAILED="request failed";
    public static final String QUERY_EXIT ="QUERY_EXIT";
    public static final String QUERY_NOT_EXIT="QUERY_NOT_EXIT";
}
