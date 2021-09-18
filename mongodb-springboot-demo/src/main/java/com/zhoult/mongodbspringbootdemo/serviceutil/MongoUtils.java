package com.zhoult.mongodbspringbootdemo.serviceutil;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
//import com.singhand.data2kafka.beans.*;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class MongoUtils {

    @Autowired
    @Qualifier("crawlerMongoTemplate")
    private  MongoTemplate crawlerMongoTemplate;

    @Autowired
    @Qualifier("testMongoTemplate")
    private  MongoTemplate testMongoTemplate;

//    @Autowired
//    @Qualifier("commercialMongoTemplate")
//    private  MongoTemplate commercialMongoTemplate;

//    @Autowired
//    @Qualifier("bidRadarTestMongoTemplate")
//    private  MongoTemplate bidRadarTestMongoTemplate;

    private final static String CRAWL_TIME = "crawl_time";
    private final static String DOMAIN = "domain";
    private final static String BID_RECORD = "bid_record";
    private final static String FAILURE_RECORD = "failure_record_info";
    private final static String FAILURE_RECORD_ID = "id";
    private final static String COMMERCIAL_BASIC_COLLECTION = "basicData";

    //无监督mongo表
    private final static String UNATTENDED_BID_LIST_PAGE = "unattended_bid_list_page";
    private final static String UNATTENDED_BID_RECORD = "unattended_bid_record";
    private final static String BID_DATA_SOURCES = "bid_data_sources";
    private final static String SOURCE_LIST_PAGE = "source_list_page";
    private final static String IS_LIST_PAGE = "is_list_page";

    public MongoCursor<Document> queryMongoBidDataSources(String domain){
        //需要正式哭，查询有无数据
        final MongoCollection<Document> collection = crawlerMongoTemplate.getCollection(BID_DATA_SOURCES);
        final BasicDBObject query = new BasicDBObject(DOMAIN,domain);
        final FindIterable<Document> documentFindIterable = collection.find(query);
        final MongoCursor<Document> iterator = documentFindIterable.iterator();
        return iterator;
    }
    /**
     * 读取工商数据的基本信息
     */
//    public MongoCursor<Document> queryAllCommercialBasicData(){
//        MongoCollection<Document> collection = commercialMongoTemplate.getCollection(COMMERCIAL_BASIC_COLLECTION);
//        FindIterable<Document> documents = collection.find();
//        MongoCursor<Document> iterator = documents.iterator();
//        return iterator;
//    }

//    /**
//     * 从mongo查询数据
//     * @param domain 域名
//     * @param size  读取条数
//     * @param from  从第几条开始读
//     * @return
//     */
//    public MongoCursor<Document> queryFromMongo(String collectName,String domain,int size,int from){
//        final MongoCollection<Document> collection = crawlerMongoTemplate.getCollection(collectName);
//        final BasicDBObject domainEq = new BasicDBObject(DOMAIN,domain);
//        final BasicDBObject timeGt = new BasicDBObject(CRAWL_TIME, new BasicDBObject("$gt", 1L));
//        BasicDBList basicDBList = new BasicDBList();
//        basicDBList.add(domainEq);
//        basicDBList.add(timeGt);
//        final BasicDBObject query = new BasicDBObject("$and", basicDBList);
//        final FindIterable<Document> documentFindIterable = collection.find(query).skip(from).limit(size);
//        final MongoCursor<Document> iterator = documentFindIterable.iterator();
//        return iterator;
//    }

//    public MongoCursor<Document> queryFromMongo(String domain,int size,int from){
//        final MongoCollection<Document> collection = crawlerMongoTemplate.getCollection(BID_RECORD);
//        final BasicDBObject domainEq = new BasicDBObject(DOMAIN,domain);
//        final BasicDBObject timeGt = new BasicDBObject(CRAWL_TIME, new BasicDBObject("$gt", 1L));
//        BasicDBList basicDBList = new BasicDBList();
//        basicDBList.add(domainEq);
//        basicDBList.add(timeGt);
//        final BasicDBObject query = new BasicDBObject("$and", basicDBList);
//        final FindIterable<Document> documentFindIterable = collection.find(query).skip(from).limit(size);
//        final MongoCursor<Document> iterator = documentFindIterable.iterator();
//        return iterator;
//    }

//    public MongoCursor<Document> queryFromMongo(String domain){
//        final MongoCollection<Document> collection = crawlerMongoTemplate.getCollection(BID_RECORD);
//        final BasicDBObject domainEq = new BasicDBObject(DOMAIN,domain);
//        final BasicDBObject timeGt = new BasicDBObject(CRAWL_TIME, new BasicDBObject("$gt", 1L));
//        BasicDBList basicDBList = new BasicDBList();
//        basicDBList.add(domainEq);
//        basicDBList.add(timeGt);
//        final BasicDBObject query = new BasicDBObject("$and", basicDBList);
//        final FindIterable<Document> documentFindIterable = collection.find(query);
//        final MongoCursor<Document> iterator = documentFindIterable.iterator();
//        return iterator;
//    }
//
//    public List<String> queryFromMongoDistinct(){
//        TypedAggregation<BidRecord> agg = Aggregation.newAggregation(BidRecord.class,
//                Aggregation.group(DOMAIN).
//                        first(DOMAIN).as("domain").
//                        addToSet(DOMAIN).as("domain")
//        );
//        AggregationResults<BidRecord> result = crawlerMongoTemplate.aggregate(agg,BidRecord.class);
//        List<String> domains=result.getMappedResults().parallelStream().map(m->
//        {return m.getDomain();}).collect(Collectors.toList());
//        return domains;
//    }
//
//    public MongoCursor<Document> queryFromMongo(String domain,Long time){
//        final MongoCollection<Document> collection = crawlerMongoTemplate.getCollection(BID_RECORD);
//        final BasicDBObject domainEq = new BasicDBObject(DOMAIN,domain);
//        final BasicDBObject timeGt = new BasicDBObject(CRAWL_TIME, new BasicDBObject("$gte",time));
//        BasicDBList basicDBList = new BasicDBList();
//        basicDBList.add(domainEq);
//        basicDBList.add(timeGt);
//        final BasicDBObject query = new BasicDBObject("$and", basicDBList);
//        final FindIterable<Document> documentFindIterable = collection.find(query);
//        final MongoCursor<Document> iterator = documentFindIterable.iterator();
//        return iterator;
//    }
//
//    public long queryFromMongoDataNumber(String domain,Long time){
//        final MongoCollection<Document> collection = crawlerMongoTemplate.getCollection(BID_RECORD);
//        final BasicDBObject domainEq = new BasicDBObject(DOMAIN,domain);
//        final BasicDBObject timeGt = new BasicDBObject(CRAWL_TIME, new BasicDBObject("$gte",time));
//        BasicDBList basicDBList = new BasicDBList();
//        basicDBList.add(domainEq);
//        basicDBList.add(timeGt);
//        final BasicDBObject query = new BasicDBObject("$and", basicDBList);
//        long documents = collection.countDocuments(query);
//        return documents;
//    }
//
//    public MongoCursor<Document> queryFromMongoFailure(){
//        final MongoCollection<Document> collection = testMongoTemplate.getCollection(FAILURE_RECORD);
//        final FindIterable<Document> documentFindIterable = collection.find();
//        final MongoCursor<Document> iterator = documentFindIterable.iterator();
//        return iterator;
//    }
//
//    public void removeFromMongoFailure(String id){
//        if (StringUtils.isEmpty(id)) return;
//        Query query=new Query(Criteria.where("id").exists(true).andOperator(Criteria.where("id").is(id)));
//        testMongoTemplate.remove(query,FailureRecordInfo.class);
//    }
//
//    public void insertException(ErrorProcessInfo errorInfo){
//        if(errorInfo!=null){
//            final String id = errorInfo.getId();
//            Query query = Query.query(Criteria.where("id").is(id));
//            if(testMongoTemplate.exists(query,ErrorProcessInfo.class)){
//                //TODO 暂不处理
//            }else{
//                errorInfo.setCreateTime(System.currentTimeMillis());
//                testMongoTemplate.insert(errorInfo);
//            }
//        }
//    }
//
//    public void upsertKafkaSendCount(String domain,int count,boolean isComplete){
//        Query query = Query.query(Criteria.where("domain").is(domain));
//        Update update = new Update();
//        update.set("count",count);
//        update.set("modifyTime",Calendar.getInstance().getTime().toString());
//        update.set("isComplete",isComplete);
//        testMongoTemplate.upsert(query,update,KafkaSendCount.class);
//    }
//
//    public static void main(String[] args) {
//        System.out.println(Calendar.getInstance().getTime().toString());
//    }
//
//    public void insertFaliedRecord(String id,String errInfo){
//        if(StringUtils.isNotBlank(id)){
//            Query query = Query.query(Criteria.where("_id").is(id));
//            Update update = new Update();
//            update.set("errInfo",errInfo);
//            testMongoTemplate.upsert(query,update,FailureRecordInfo.class);
//        }
//    }
//
//    public void insertDataProcessResult(DataProcessResult dataProcessResult){
//        if(dataProcessResult!=null){
//            final String id = dataProcessResult.getId();
//            Query query = Query.query(Criteria.where("id").is(id));
//            if(testMongoTemplate.exists(query,DataProcessResult.class)){
//                //TODO 暂不处理
//            }else{
//                dataProcessResult.setCreateTime(System.currentTimeMillis());
//                testMongoTemplate.insert(dataProcessResult);
//            }
//        }
//    }
//
//    /**
//     * 得到当前网站的所有标讯(true)列表页
//     */
//    public MongoCursor<Document> queryTestFromListPageMongo(String doamin,int size,int from){
//        final MongoCollection<Document> collection = crawlerMongoTemplate.getCollection(UNATTENDED_BID_RECORD);
//        final BasicDBObject urlEq = new BasicDBObject(DOMAIN,doamin);
//        final BasicDBObject isListPage = new BasicDBObject(IS_LIST_PAGE,true);
//        final BasicDBObject timeGt = new BasicDBObject(CRAWL_TIME, new BasicDBObject("$gt", 1L));
//        BasicDBList basicDBList = new BasicDBList();
//        basicDBList.add(urlEq);
//        basicDBList.add(isListPage);
//        basicDBList.add(timeGt);
//        final BasicDBObject query = new BasicDBObject("$and", basicDBList);
//        if (size==0 && from==0){
//            final FindIterable<Document> documentFindIterable = collection.find(query);
//            final MongoCursor<Document> iterator = documentFindIterable.iterator();
//            return iterator;
//        }else {
//            final FindIterable<Document> documentFindIterable = collection.find(query).skip(from).limit(size);
//            final MongoCursor<Document> iterator = documentFindIterable.iterator();
//            return iterator;
//        }
//
//    }
//
//    public Long queryRecordCount(String doamin){
//        Query query=Query.query(Criteria.where(DOMAIN).is(doamin).and(IS_LIST_PAGE).is(true));
//        return crawlerMongoTemplate.count(query, UnattendedBidRecord.class);
//    }
//
//
//    /**
//     * 得到当前网站列表页下的所有详情页
//     */
//    public MongoCursor<Document> queryTestFromMongo(String url,int size,int from){
//        final MongoCollection<Document> collection = crawlerMongoTemplate.getCollection(UNATTENDED_BID_RECORD);
//        final BasicDBObject urlEq = new BasicDBObject(SOURCE_LIST_PAGE,url);
//        final BasicDBObject isListPage = new BasicDBObject(IS_LIST_PAGE,false);
//        final BasicDBObject timeGt = new BasicDBObject(CRAWL_TIME, new BasicDBObject("$gt", 1L));
//        BasicDBList basicDBList = new BasicDBList();
//        basicDBList.add(urlEq);
//        basicDBList.add(isListPage);
//        basicDBList.add(timeGt);
//        final BasicDBObject query = new BasicDBObject("$and", basicDBList);
//        if (size==0 && from==0){
//            final FindIterable<Document> documentFindIterable = collection.find(query);
//            final MongoCursor<Document> iterator = documentFindIterable.iterator();
//            return iterator;
//        }else {
//            final FindIterable<Document> documentFindIterable = collection.find(query).skip(from).limit(size);
//            final MongoCursor<Document> iterator = documentFindIterable.iterator();
//            return iterator;
//        }
//    }
//
//    //修改列表页判断状态
//    public void upsertKafkaSendUnsupervised(String id,boolean isListPage){
//        Query query = Query.query(Criteria.where("id").is(id));
//        Update update = new Update();
//        update.set(IS_LIST_PAGE,isListPage);
//        crawlerMongoTemplate.upsert(query,update,UnattendedBidRecord.class);
//    }
//
//    public MongoCursor<Document> queryMongoRecordDomain(){
//        final MongoCollection<Document> collection = crawlerMongoTemplate.getCollection(UNATTENDED_BID_RECORD);
//        final BasicDBObject isListPage = new BasicDBObject(IS_LIST_PAGE,true);
//        final BasicDBObject timeGt = new BasicDBObject(CRAWL_TIME, new BasicDBObject("$gt", 1L));
//        BasicDBList basicDBList = new BasicDBList();
//        basicDBList.add(isListPage);
//        basicDBList.add(timeGt);
//        final BasicDBObject query = new BasicDBObject("$and", basicDBList);
//        final FindIterable<Document> documentFindIterable = collection.find(query);
//        final MongoCursor<Document> iterator = documentFindIterable.iterator();
//        return iterator;
//
//    }
//
//    public void insertBidDataSources(BidDataSources dataSources){
//        if(StringUtils.isNotBlank(dataSources.getDomain())){
//            Query query = Query.query(Criteria.where("domain").is(dataSources.getDomain()));
//            Update update = new Update();
//            update.set("name",dataSources.getName());
//            update.set("domain",dataSources.getDomain());
//            update.set("type",dataSources.getType());
//            update.set("priority",dataSources.getPriority());
//            update.set("crawl_time",System.currentTimeMillis());
//            crawlerMongoTemplate.upsert(query,update,BidDataSources.class);
//        }
//    }
//


}
