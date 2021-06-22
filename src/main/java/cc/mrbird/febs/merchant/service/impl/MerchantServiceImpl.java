package cc.mrbird.febs.merchant.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.merchant.entity.Merchant;
import cc.mrbird.febs.merchant.mapper.MerchantMapper;
import cc.mrbird.febs.merchant.service.IMerchantService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * @author A stubborn man
 * @date 2021-03-08 10:35:20
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant> implements IMerchantService {

    private final MerchantMapper merchantMapper;

    @Override
    public IPage<Merchant> findMerchants(QueryRequest request, Merchant merchant) {
        Page<Merchant> page = new Page<>(request.getPageNum(), request.getPageSize());
        QueryWrapper<Merchant> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(merchant);
        page.setSearchCount(false);
        page.setTotal(baseMapper.selectCount(queryWrapper));
        page = this.baseMapper.selectPage(page,queryWrapper);
        List<Merchant> list= page.getRecords();
        SortUtil.handlePageSort(request, page, "merchantId", FebsConstant.ORDER_DESC, true);
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Merchant> findMerchants(Merchant merchant) {
	    LambdaQueryWrapper<Merchant> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createMerchant(Merchant merchant) {
        this.save(merchant);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMerchant(Merchant merchant) {
        this.saveOrUpdate(merchant);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMerchant(Merchant merchant) {
        LambdaQueryWrapper<Merchant> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
