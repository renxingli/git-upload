package cc.mrbird.febs.material.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.material.entity.TMateria;
import cc.mrbird.febs.material.mapper.TMateriaMapper;
import cc.mrbird.febs.material.service.ITMateriaService;
import cc.mrbird.febs.shop.entity.Shop;
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
 * @author bjw
 * @date 2021-03-01 11:31:45
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TMateriaServiceImpl extends ServiceImpl<TMateriaMapper, TMateria> implements ITMateriaService {

    private final TMateriaMapper tMateriaMapper;

    @Override
    public IPage<TMateria> findTMaterias(QueryRequest request, TMateria tMateria) {
        LambdaQueryWrapper<TMateria> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<TMateria> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.counttMateriaDetail(tMateria));
        SortUtil.handlePageSort(request, page, "MATERIAL_ID", FebsConstant.ORDER_ASC, false);
        return baseMapper.findtMateriaDetailPage(page,tMateria);
    }

    @Override
    public List<TMateria> findTMaterias(TMateria tMateria) {
	    LambdaQueryWrapper<TMateria> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createTMateria(TMateria tMateria) {
        this.save(tMateria);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTMateria(TMateria tMateria) {
        this.saveOrUpdate(tMateria);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTMateria(TMateria tMateria) {
        LambdaQueryWrapper<TMateria> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
