package com.zhoult.mongodbspringbootdemo;

import com.zhoult.mongodbspringbootdemo.serviceutil.ToGetKeywords;
import com.zhoult.mongodbspringbootdemo.serviceutil.impl.ToGetKeywordsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 面如冠玉、出类拔萃
 * @version 1.0
 * @date 2021/9/14 上午11:12
 */
@Slf4j
@Component
public class RunSearchTask {
    @Autowired
    ToGetKeywords toGetKeywords;
    /**
     * 表示方法执行完成后3600秒
     * 表示方法执行完成后在每个小时30分钟时刻执行
     * * 第一位，表示秒，取值0-59
     * * 第二位，表示分，取值0-59
     * * 第三位，表示小时，取值0-23
     * * 第四位，日期天/日，取值1-31
     * * 第五位，日期月份，取值1-12
     * * 第六位，星期，取值1-7，星期一，星期二...，注：不是第1周，第二周的意思
     *           另外：1表示星期天，2表示星期一。
     * * 第7为，年份，可以留空，取值1970-2099
     *
     * (*)星号：可以理解为每的意思，每秒，每分，每天，每月，每年...
     * (?)问号：问号只能出现在日期和星期这两个位置。
     * (-)减号：表达一个范围，如在小时字段中使用“10-12”，则表示从10到12点，即10,11,12
     * (,)逗号：表达一个列表值，如在星期字段中使用“1,2,4”，则表示星期一，星期二，星期四
     * (/)斜杠：如：x/y，x是开始值，y是步长，比如在第一位（秒） 0/15就是，从0秒开始，每15秒，最后就是0，15，30，45，60    另：y，等同于0/y
     */
    /**
     *     表示方法执行完成后8秒
     *     @Scheduled(fixedDelay = 8000)
     *     @Scheduled(fixedRate = 9000)
     */
    @Scheduled(cron = "0 30 * ? * ? ")
    public void function(){
        log.info("*********面如冠玉、出类拔萃开始执行一次任务***************" );
        toGetKeywords.mainTask();
        log.info("*********执行一次成功***************" );
        System.out.println("************1 hours**************");
    }
}
