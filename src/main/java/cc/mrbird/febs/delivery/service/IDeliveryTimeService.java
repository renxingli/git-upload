package cc.mrbird.febs.delivery.service;

import cc.mrbird.febs.delivery.entity.DeliveryTime;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author A stubborn man
 * @date 2021-03-05 11:38:07
 */
public interface IDeliveryTimeService extends IService<DeliveryTime> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param deliveryTime deliveryTime
     * @return IPage<DeliveryTime>
     */
    IPage<DeliveryTime> findDeliveryTimes(QueryRequest request, DeliveryTime deliveryTime);

    /**
     * 查询（所有）
     *
     * @param deliveryTime deliveryTime
     * @return List<DeliveryTime>
     */
    List<DeliveryTime> findDeliveryTimes(DeliveryTime deliveryTime);

    /**
     * 新增
     *
     * @param deliveryTime deliveryTime
     */
    void createDeliveryTime(DeliveryTime deliveryTime);

    /**
     * 修改
     *
     * @param deliveryTime deliveryTime
     */
    void updateDeliveryTime(DeliveryTime deliveryTime);

    /**
     * 删除
     *
     * @param deliveryTime deliveryTime
     */
    void deleteDeliveryTime(DeliveryTime deliveryTime);
}
