package cc.mrbird.febs.system.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Region;
import cc.mrbird.febs.system.mapper.RegionMapper;
import cc.mrbird.febs.system.service.IRegionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import javafx.stage.StageStyle;
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
 * Service实现
 *
 * @author weiZiHao
 * @date 2021-03-08 10:04:00
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements IRegionService {

    private final RegionMapper regionMapper;

    @Override
    public IPage<Region> findRegions(QueryRequest request, Region region) {
        QueryWrapper<Region> queryWrapper = new QueryWrapper<>();
        // TODO 设置查询条件
        System.err.println(region);
        if (region.getMername() != null) {
            queryWrapper.like("mername", region.getMername());
            region.setMername(null);
        }
        queryWrapper.setEntity(region);
        Page<Region> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Region> findRegions(Region region) {
        QueryWrapper<Region> queryWrapper = new QueryWrapper<>();
        // TODO 设置查询条件
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRegion(Region region) {
        this.save(region);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRegion(Region region) {
        this.saveOrUpdate(region);
        region = regionMapper.selectById(region.getId());
        updateParent(region);
    }

    public void updateParent(Region region) {
        if (region.getPid() != 0) {
            Region parent = regionMapper.selectById(region.getPid());
            parent.setStateId(1);
            regionMapper.updateById(parent);
            updateParent(parent);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRegion(Region region) {
        LambdaQueryWrapper<Region> wrapper = new LambdaQueryWrapper<>();
        // TODO 设置删除条件
        this.remove(wrapper);
    }
}
