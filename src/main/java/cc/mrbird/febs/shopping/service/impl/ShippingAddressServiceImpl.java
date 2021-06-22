package cc.mrbird.febs.shopping.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.shopping.entity.ShippingAddress;
import cc.mrbird.febs.shopping.mapper.ShippingAddressMapper;
import cc.mrbird.febs.shopping.service.IShippingAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 *  Service实现
 *
 * @author weiZiHao
 * @date 2021-03-02 14:05:22
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ShippingAddressServiceImpl extends ServiceImpl<ShippingAddressMapper, ShippingAddress> implements IShippingAddressService {

    private final ShippingAddressMapper shippingAddressMapper;

    @Override
    public IPage<ShippingAddress> findShippingAddresss(QueryRequest request, ShippingAddress shippingAddress) {
        LambdaQueryWrapper<ShippingAddress> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<ShippingAddress> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<ShippingAddress> findShippingAddresss(ShippingAddress shippingAddress) {
	    LambdaQueryWrapper<ShippingAddress> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createShippingAddress(ShippingAddress shippingAddress) {
        this.save(shippingAddress);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateShippingAddress(ShippingAddress shippingAddress) {
        this.saveOrUpdate(shippingAddress);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteShippingAddress(ShippingAddress shippingAddress) {
        LambdaQueryWrapper<ShippingAddress> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
