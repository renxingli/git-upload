package cc.mrbird.febs.stocks.mapper;

import cc.mrbird.febs.material.entity.TMateria;
import cc.mrbird.febs.shop.entity.Shop;
import cc.mrbird.febs.stocks.entity.Stocks;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 *  Mapper
 *
 * @author bjw
 * @date 2021-03-02 16:09:27
 */
public interface StocksMapper extends BaseMapper<Stocks> {

    long countstocksDetail(@Param("stocks") Stocks stocks);

    <T> IPage<Stocks> findtstocksDetailPage(Page<T> page, @Param("stocks") Stocks stocks);

}
