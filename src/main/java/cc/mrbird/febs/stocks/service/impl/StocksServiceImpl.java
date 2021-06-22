package cc.mrbird.febs.stocks.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.material.entity.TMateria;
import cc.mrbird.febs.shop.entity.Shop;
import cc.mrbird.febs.stocks.entity.Stocks;
import cc.mrbird.febs.stocks.mapper.StocksMapper;
import cc.mrbird.febs.stocks.service.IStocksService;
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
 * @author bjw
 * @date 2021-03-02 16:09:27
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class StocksServiceImpl extends ServiceImpl<StocksMapper, Stocks> implements IStocksService {

    private final StocksMapper stocksMapper;


    @Override
    public IPage<Stocks> findStocks(QueryRequest request, Stocks stocks) {
        LambdaQueryWrapper<TMateria> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Stocks> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countstocksDetail(stocks));
        SortUtil.handlePageSort(request, page, "RAWMATERIAL_ID", FebsConstant.ORDER_ASC, false);
        return baseMapper.findtstocksDetailPage(page,stocks);
    }


    @Override
    public IPage<Stocks> findStockss(QueryRequest request, Stocks stocks) {
        return null;
    }

    @Override
    public List<Stocks> findStockss(Stocks stocks) {
        return null;
    }


    public Stocks findStockID(Integer rawmaterialId) {
        Stocks stocks = baseMapper.selectById(rawmaterialId);
		return stocks;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createStocks(Stocks stocks) {
        this.save(stocks);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStocks(Stocks stocks) {
        this.saveOrUpdate(stocks);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteStocks(String[] rawmaterialIds) {
        List<String> list = Arrays.asList(rawmaterialIds);
        LambdaQueryWrapper<Stocks> wrapper = new LambdaQueryWrapper<>();
        baseMapper.delete(wrapper.in(Stocks::getRawmaterialId,list));
    }

}
