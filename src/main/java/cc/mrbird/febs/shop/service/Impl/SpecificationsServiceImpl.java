package cc.mrbird.febs.shop.service.Impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.shop.entity.Specifications;
import cc.mrbird.febs.shop.mapper.SpecificationsMapper;
import cc.mrbird.febs.shop.service.ISpecificationsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.swing.text.html.parser.Entity;
import java.util.List;

/**
 * Service实现
 *
 * @author weiZiHao
 * @date 2021-03-02 10:56:43
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SpecificationsServiceImpl extends ServiceImpl<SpecificationsMapper, Specifications> implements ISpecificationsService {

    private final SpecificationsMapper specificationsMapper;


    @Override
    public IPage<Specifications> findSpecificationss(QueryRequest request, Specifications specifications) {
        LambdaQueryWrapper<Specifications> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        queryWrapper.setEntity(specifications);
        Page<Specifications> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Specifications> findSpecificationss(Specifications specifications) {
        LambdaQueryWrapper<Specifications> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createSpecifications(Specifications specifications) {
        this.save(specifications);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSpecifications(Specifications specifications) {
        this.saveOrUpdate(specifications);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSpecifications(Specifications specifications) {
        LambdaQueryWrapper<Specifications> wrapper = new LambdaQueryWrapper<>();
        // TODO 设置删除条件
        wrapper.setEntity(specifications);
        this.remove(wrapper);
    }
}
