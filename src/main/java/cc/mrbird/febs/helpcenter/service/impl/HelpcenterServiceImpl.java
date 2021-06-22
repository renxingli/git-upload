package cc.mrbird.febs.helpcenter.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.helpcenter.entity.Helpcenter;
import cc.mrbird.febs.helpcenter.mapper.HelpcenterMapper;
import cc.mrbird.febs.helpcenter.service.IHelpcenterService;
import cc.mrbird.febs.news.entity.News;
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
import java.util.List;

/**
 *  Service实现
 *
 * @author A stubborn man
 * @date 2021-03-05 09:33:25
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class HelpcenterServiceImpl extends ServiceImpl<HelpcenterMapper, Helpcenter> implements IHelpcenterService {

    private final HelpcenterMapper helpcenterMapper;

    @Override
    public IPage<Helpcenter> findHelpcenters(QueryRequest request, Helpcenter helpcenter) {
        Page<Helpcenter> page = new Page<>(request.getPageNum(), request.getPageSize());
        QueryWrapper<Helpcenter> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(helpcenter);
        page.setSearchCount(false);
        page.setTotal(baseMapper.selectCount(queryWrapper));
        page = this.baseMapper.selectPage(page,queryWrapper);
        List<Helpcenter> list= page.getRecords();
        SortUtil.handlePageSort(request, page, "helpcenterId", FebsConstant.ORDER_DESC, true);
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Helpcenter> findHelpcenters(Helpcenter helpcenter) {
	    LambdaQueryWrapper<Helpcenter> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createHelpcenter(Helpcenter helpcenter) {
        this.save(helpcenter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateHelpcenter(Helpcenter helpcenter) {
        this.saveOrUpdate(helpcenter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHelpcenter(Helpcenter helpcenter) {
        LambdaQueryWrapper<Helpcenter> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}

    public Helpcenter helpDetail(Integer helpcenterId){
        QueryWrapper<Helpcenter> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("HELPCENTER_ID","HELPCENTER_TITLE","HEPLCENTER_CONTENT","CREATE_TIME","MODIFY_TIME").eq("HELPCENTER_ID",helpcenterId);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHelps(String[] helpcenterIds) {
        List<String> list = Arrays.asList(helpcenterIds);
        LambdaQueryWrapper<Helpcenter> wrapper = new LambdaQueryWrapper<>();
        baseMapper.delete(wrapper.in(Helpcenter::getHelpcenterId,list));
    }

}
