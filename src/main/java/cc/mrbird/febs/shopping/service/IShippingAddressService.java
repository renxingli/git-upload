package cc.mrbird.febs.shopping.service;

import cc.mrbird.febs.shopping.entity.ShippingAddress;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author weiZiHao
 * @date 2021-03-02 14:05:22
 */
public interface IShippingAddressService extends IService<ShippingAddress> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param shippingAddress shippingAddress
     * @return IPage<ShippingAddress>
     */
    IPage<ShippingAddress> findShippingAddresss(QueryRequest request, ShippingAddress shippingAddress);

    /**
     * 查询（所有）
     *
     * @param shippingAddress shippingAddress
     * @return List<ShippingAddress>
     */
    List<ShippingAddress> findShippingAddresss(ShippingAddress shippingAddress);

    /**
     * 新增
     *
     * @param shippingAddress shippingAddress
     */
    void createShippingAddress(ShippingAddress shippingAddress);

    /**
     * 修改
     *
     * @param shippingAddress shippingAddress
     */
    void updateShippingAddress(ShippingAddress shippingAddress);

    /**
     * 删除
     *
     * @param shippingAddress shippingAddress
     */
    void deleteShippingAddress(ShippingAddress shippingAddress);
}
