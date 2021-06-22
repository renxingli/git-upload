package cc.mrbird.febs.news.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.news.entity.News;
import cc.mrbird.febs.news.service.INewsService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 *  Controller
 *
 * @author A stubborn man
 * @date 2021-03-03 17:25:20
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class NewsController extends BaseController {

    private final INewsService newsService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "news")
    public String newsIndex(){
        return FebsUtil.view("news/news");
    }

    @GetMapping("news")
    @ResponseBody
    @RequiresPermissions("news:list")
    public FebsResponse getAllNewss(News news) {
        return new FebsResponse().success().data(newsService.findNewss(news));
    }

    @GetMapping("news/list")
    @ResponseBody
    public FebsResponse newsList(QueryRequest request, News news) {
        Map<String, Object> dataTable = getDataTable(this.newsService.findNewss(request, news));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增News", exceptionMessage = "新增News失败")
    @PostMapping("news")
    @ResponseBody
    public FebsResponse addNews(@Valid News news) {
        this.newsService.createNews(news);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除News", exceptionMessage = "删除News失败")
    @GetMapping("news/delete")
    @ResponseBody
    @RequiresPermissions("news:delete")
    public FebsResponse deleteNews(News news) {
        this.newsService.deleteNews(news);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改News", exceptionMessage = "修改News失败")
    @PostMapping("news/update")
    @ResponseBody
    public FebsResponse updateNews(News news) {
        this.newsService.updateNews(news);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改News", exceptionMessage = "导出Excel失败")
    @GetMapping("news/excel")
    @ResponseBody
    public void export(QueryRequest queryRequest, News news, HttpServletResponse response) {
        List<News> newss = this.newsService.findNewss(queryRequest, news).getRecords();
        ExcelKit.$Export(News.class, response).downXlsx(newss, false);
    }

    @GetMapping("/news/delete/{newsIds}")
    @ResponseBody
    public FebsResponse newsDelete(@PathVariable String[] newsIds){
        newsService.deleteNews(newsIds);
        return new FebsResponse().success();
    }

}
