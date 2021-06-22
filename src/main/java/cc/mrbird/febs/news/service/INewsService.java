package cc.mrbird.febs.news.service;

import cc.mrbird.febs.news.entity.News;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author A stubborn man
 * @date 2021-03-03 17:25:20
 */
public interface INewsService extends IService<News> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param news news
     * @return IPage<News>
     */
    IPage<News> findNewss(QueryRequest request, News news);

    /**
     * 查询（所有）
     *
     * @param news news
     * @return List<News>
     */
    List<News> findNewss(News news);

    /**
     * 新增
     *
     * @param news news
     */
    void createNews(News news);

    /**
     * 修改
     *
     * @param news news
     */
    void updateNews(News news);

    /**
     * 删除
     *
     * @param news news
     */
    void deleteNews(News news);

    void deleteNews(String[] newsIds);
}
