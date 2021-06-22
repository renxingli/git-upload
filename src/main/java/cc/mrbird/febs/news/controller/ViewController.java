package cc.mrbird.febs.news.controller;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.news.entity.News;
import cc.mrbird.febs.news.service.impl.NewsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;

/**
 * @author A stubborn man
 * @create 2021/3/4 9:43
 * @Description 新闻跳转Controller
 */

@Controller("newsView")
@CrossOrigin
@RequiredArgsConstructor
public class ViewController {

    private final NewsServiceImpl newsService;
    /**
     * 跳转新闻增加页
     * */
    @GetMapping(FebsConstant.VIEW_PREFIX+"system/news/add")
    public String newsAdd(){
        return FebsUtil.view("system/news/newsAdd");
    }

    /**
     * 跳转新闻详情页
     * */
    @GetMapping(FebsConstant.VIEW_PREFIX+"system/news/detail/{newsId}")
    public String newsDetail(@PathVariable Integer newsId, Model model){
        News news = newsService.newsDetail(newsId);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = sf.format(news.getCreateTime());
        String format2 = sf.format(news.getModifyTime());
        news.setTime1(format1);
        news.setTime2(format2);
        model.addAttribute("news",news);
        return FebsUtil.view("system/news/newsDetail");
    }

    /**
     * 跳转新闻修改页(带值)
     * */
    @GetMapping(FebsConstant.VIEW_PREFIX+"system/news/updateHtml/{newsId}")
    public String newsUpdate(@PathVariable Integer newsId,Model model){
        News news = newsService.newsDetail(newsId);
        model.addAttribute("news",news);
        return FebsUtil.view("system/news/newsUpdate");
    }
}
