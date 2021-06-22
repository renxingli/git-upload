package cc.mrbird.febs.news.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.news.entity.News;
import cc.mrbird.febs.news.mapper.NewsMapper;
import cc.mrbird.febs.news.service.INewsService;
import cc.mrbird.febs.shop.entity.Shop;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Arrays;
import java.util.List;

/**
 *  Service实现
 *
 * @author A stubborn man
 * @date 2021-03-03 17:25:20
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

    private final NewsMapper newsMapper;

    @Override
    public IPage<News> findNewss(QueryRequest request, News news) {
        Page<News> page = new Page<>(request.getPageNum(), request.getPageSize());
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(news);
        page.setSearchCount(false);
        page.setTotal(baseMapper.selectCount(queryWrapper));
        page = this.baseMapper.selectPage(page,queryWrapper);
        List<News> list= page.getRecords();
        SortUtil.handlePageSort(request, page, "newsId", FebsConstant.ORDER_DESC, true);
        return this.page(page, queryWrapper);
    }

    @Override
    public List<News> findNewss(News news) {
	    LambdaQueryWrapper<News> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createNews(News news) {
        this.save(news);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateNews(News news) {
        this.saveOrUpdate(news);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteNews(News news) {
        LambdaQueryWrapper<News> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}

	public News newsDetail(Integer newsId){
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("NEWS_ID","NEWS_TITLE","NEWS_CONTENT","CREATE_TIME","MODIFY_TIME").eq("NEWS_ID",newsId);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteNews(String[] newsIds) {
        List<String> list = Arrays.asList(newsIds);
        LambdaQueryWrapper<News> wrapper = new LambdaQueryWrapper<>();
        baseMapper.delete(wrapper.in(News::getNewsId,list));
    }
}
