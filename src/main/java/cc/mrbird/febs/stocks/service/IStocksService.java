package cc.mrbird.febs.stocks.service;

import cc.mrbird.febs.stocks.entity.Stocks;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author bjw
 * @date 2021-03-02 16:09:27
 */
public interface IStocksService extends IService<Stocks> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param stocks stocks
     * @return IPage<Stocks>
     */
    IPage<Stocks> findStockss(QueryRequest request, Stocks stocks);

    /**
     * 查询（所有）
     *
     * @param stocks stocks
     * @return List<Stocks>
     */
    List<Stocks> findStockss(Stocks stocks);

    /**
     * 新增
     *
     * @param stocks stocks
     */
    void createStocks(Stocks stocks);

    /**
     * 修改
     *
     * @param stocks stocks
     */
    void updateStocks(Stocks stocks);

    /**
     * 删除
     *
     * @param stocks stocks
     */
    void deleteStocks(String[] rawmaterialIds);

    IPage<Stocks> findStocks(QueryRequest request, Stocks stocks);

    Stocks findStockID(Integer rawmaterialId);
}
