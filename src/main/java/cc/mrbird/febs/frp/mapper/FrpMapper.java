package cc.mrbird.febs.frp.mapper;

import cc.mrbird.febs.frp.entity.Frp;
import cc.mrbird.febs.stocks.entity.Stocks;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 *  Mapper
 *
 * @author bjw
 * @date 2021-03-05 13:52:21
 */
public interface FrpMapper extends BaseMapper<Frp> {

    long countfrpDetail(@Param("frp") Frp frp);

    <T> IPage<Frp> findtfrpDetailPage(Page<T> page, @Param("frp") Frp frp);

}
