package cc.mrbird.febs.shop.mapper;

import cc.mrbird.febs.shop.entity.Shop;
import cc.mrbird.febs.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper
 *
 * @author rxl
 * @date 2021-02-23 14:54:52
 */

public interface ShopMapper extends BaseMapper<Shop> {

    long countShopDetail(@Param("shop") Shop shop);
    <T> IPage<Shop> findShopDetailPage(Page<T> page, @Param("shop") Shop shop);
}
