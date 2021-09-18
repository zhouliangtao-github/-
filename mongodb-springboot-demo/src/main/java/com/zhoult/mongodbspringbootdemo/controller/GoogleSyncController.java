package com.zhoult.mongodbspringbootdemo.controller;

import com.zhoult.mongodbspringbootdemo.common.domain.googleSyncHTMLPojo.LangType;
import com.zhoult.mongodbspringbootdemo.common.domain.googleSyncHTMLPojo.SearchResult;
import com.zhoult.mongodbspringbootdemo.common.entity.CommonResult;
import com.zhoult.mongodbspringbootdemo.common.entity.RequestSearch;
import com.zhoult.mongodbspringbootdemo.serviceutil.impl.OnlineSearchServiceIml;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 面如冠玉、出类拔萃
 * @version 1.0
 * @date 2021/9/13 上午11:26
 * 谷歌同步搜索
 */
@RestController
@RequestMapping("/GoogleSync")
public class GoogleSyncController {

    @Resource
    OnlineSearchServiceIml onlineSearchServiceIml;
    @ResponseBody
    @PostMapping("/{pageNo}/{searchKeywords}")
    public CommonResult<List<SearchResult>> onlineSearchCtr(@PathVariable("pageNo") Integer pageNo, @PathVariable("searchKeywords")String searchKeywords) {
        CommonResult<List<SearchResult>> commonResult = onlineSearchServiceIml.searchByKeywordsAndLang(pageNo,searchKeywords,LangType.en,10);
        return commonResult;
    }
/*    @ResponseBody
    @PostMapping("/{pageNo}/{searchKeywords}/{langType}/{pageSize}")
    public CommonResult<List<SearchResult>> onlineSearchCtr(@PathVariable("pageNo") Integer pageNo, @PathVariable("searchKeywords")String searchKeywords, @PathVariable("langType")Enum langType, @PathVariable("pageSize")Integer pageSize) {
        CommonResult<List<SearchResult>> commonResult = onlineSearchServiceIml.searchByKeywordsAndLang(pageNo,searchKeywords,langType,pageSize);
        return commonResult;
    }*/

    @PostMapping(value = "/search",consumes={"application/json"})
    public CommonResult<List<SearchResult>> onlineSearchCtr(@RequestBody RequestSearch requestSearch) {
//       String s = JSON.toJSONString(requestSearch);
       CommonResult<List<SearchResult>> commonResult = onlineSearchServiceIml.searchByKeywordsAndLang(
               requestSearch.getPageNo(),
               requestSearch.getSearchKeywords(),
               LangType.en,
               requestSearch.getPageSize()
       );
        return commonResult;
    }

/*    @PostMapping("/{searchKeywords}")
    public CommonResult<List<Object>> onlineSearchCtr(@RequestParam String searchKeywords) {
        List<JSON> jsons = onlineSearchServiceIml.searchByKeywordsAndLang(searchKeywords, LangType.en);
        return new CommonResult<List<JSON>>(1, CodeMessageConstant.REQUEST_SUCCESS, jsons);
    }*/
}
