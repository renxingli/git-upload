package cc.mrbird.febs.shop.service;

import cc.mrbird.febs.shop.entity.Shop;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Service接口
 *
 * @author rxl
 * @date 2021-02-23 14:54:52
 */
public interface IShopService extends IService<Shop> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param shop shop
     * @return IPage<Shop>
     */
    IPage<Shop> findShops(QueryRequest request, Shop shop);

    /**
     * 查询（所有）
     *
     * @param  commodityId
     * @return List<Shop>
     */
    Shop findShop(Integer commodityId);

    /**
     * 新增
     *
     * @param shop shop
     */
    void createShop(Shop shop);

    /**
     * 修改
     *
     * @param shop shop
     */
    void updateShop(Shop shop);

    /**
     * 删除
     *
     * @param shop shop
     */
    void deleteShop(String[] shopIds);

    /**
     * 删除规格
     * */
    void deleteGG(String[] gg);

}
