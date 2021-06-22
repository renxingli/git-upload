package cc.mrbird.febs.thesaurus.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.material.entity.TMateria;
import cc.mrbird.febs.stocks.entity.Stocks;
import cc.mrbird.febs.thesaurus.entity.Thesaurus;
import cc.mrbird.febs.thesaurus.mapper.ThesaurusMapper;
import cc.mrbird.febs.thesaurus.service.IThesaurusService;
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
 * @author bjw
 * @date 2021-03-04 10:12:23
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ThesaurusServiceImpl extends ServiceImpl<ThesaurusMapper, Thesaurus> implements IThesaurusService {

    private final ThesaurusMapper thesaurusMapper;

    @Override
    public IPage<Thesaurus> findThesauruss(QueryRequest request, Thesaurus thesaurus) {
        LambdaQueryWrapper<TMateria> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Stocks> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countthesaurusDetail(thesaurus));
        SortUtil.handlePageSort(request, page, "THESAURUS_ID", FebsConstant.ORDER_ASC, false);
        return baseMapper.findtthesaurusDetailPage(page,thesaurus);
    }

    @Override
    public List<Thesaurus> findThesauruss(Thesaurus thesaurus) {
	    LambdaQueryWrapper<Thesaurus> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createThesaurus(Thesaurus thesaurus) {
        this.save(thesaurus);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateThesaurus(Thesaurus thesaurus) {
        this.saveOrUpdate(thesaurus);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteThesaurus(Thesaurus thesaurus) {
        LambdaQueryWrapper<Thesaurus> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletethesaurus(String[] thesaurusIds) {
        List<String> list = Arrays.asList(thesaurusIds);
        LambdaQueryWrapper<Thesaurus> wrapper = new LambdaQueryWrapper<>();
        baseMapper.delete(wrapper.in(Thesaurus::getThesaurusId,list));
    }


    public Thesaurus findthesaurusID(Integer thesaurusId) {
        Thesaurus thesaurus = baseMapper.selectById(thesaurusId);
        return thesaurus;
    }
}
