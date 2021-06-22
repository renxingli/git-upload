package cc.mrbird.febs.delivery.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.delivery.entity.DeliveryTime;
import cc.mrbird.febs.delivery.mapper.DeliveryTimeMapper;
import cc.mrbird.febs.delivery.service.IDeliveryTimeService;
import cc.mrbird.febs.helpcenter.entity.Helpcenter;
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
import java.util.Date;
import java.util.List;

/**
 *  Service实现
 *
 * @author A stubborn man
 * @date 2021-03-05 11:38:07
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DeliveryTimeServiceImpl extends ServiceImpl<DeliveryTimeMapper, DeliveryTime> implements IDeliveryTimeService {

    private final DeliveryTimeMapper deliveryTimeMapper;

    @Override
    public IPage<DeliveryTime> findDeliveryTimes(QueryRequest request, DeliveryTime deliveryTime) {
        Page<DeliveryTime> page = new Page<>(request.getPageNum(), request.getPageSize());
        QueryWrapper<DeliveryTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(deliveryTime);
        page.setSearchCount(false);
        page.setTotal(baseMapper.selectCount(queryWrapper));
        page = this.baseMapper.selectPage(page,queryWrapper);
        List<DeliveryTime> list= page.getRecords();
        SortUtil.handlePageSort(request, page, "deliveryTimeId", FebsConstant.ORDER_DESC, true);
        return this.page(page, queryWrapper);
    }

    @Override
    public List<DeliveryTime> findDeliveryTimes(DeliveryTime deliveryTime) {
	    LambdaQueryWrapper<DeliveryTime> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDeliveryTime(DeliveryTime deliveryTime) {
        deliveryTime.setCreateTime(new Date());
        deliveryTime.setModifyTime(new Date());
        this.save(deliveryTime);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDeliveryTime(DeliveryTime deliveryTime) {
        deliveryTime.setModifyTime(new Date());
        this.saveOrUpdate(deliveryTime);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDeliveryTime(DeliveryTime deliveryTime) {
        LambdaQueryWrapper<DeliveryTime> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}

    public DeliveryTime deliveryDetail(Integer deliveryTimeId){
        QueryWrapper<DeliveryTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DELIVERY_TIME_ID","REMARK","DISCOUNT","CREATE_TIME","MODIFY_TIME").eq("DELIVERY_TIME_ID",deliveryTimeId);
        return baseMapper.selectOne(queryWrapper);
    }

    public void deleteDelivers(String[] deliveryTimeIds) {
        List<String> list = Arrays.asList(deliveryTimeIds);
        LambdaQueryWrapper<DeliveryTime> wrapper = new LambdaQueryWrapper<>();
        baseMapper.delete(wrapper.in(DeliveryTime::getDeliveryTimeId,list));
    }
}
