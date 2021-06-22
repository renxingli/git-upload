package cc.mrbird.febs.tampp.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.frp.entity.Frp;
import cc.mrbird.febs.tampp.entity.Mamapping;
import cc.mrbird.febs.tampp.mapper.MamappingMapper;
import cc.mrbird.febs.tampp.service.IMamappingService;
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
 * @date 2021-03-06 14:02:49
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MamappingServiceImpl extends ServiceImpl<MamappingMapper, Mamapping> implements IMamappingService {

    private final MamappingMapper mamappingMapper;

    @Override
    public IPage<Mamapping> findMamappings(QueryRequest request, Mamapping mamapping) {
        LambdaQueryWrapper<Mamapping> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Mamapping> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Mamapping> findMamappings(Mamapping mamapping) {
	    LambdaQueryWrapper<Mamapping> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createMamapping(Mamapping mamapping) {
        this.save(mamapping);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMamapping(Mamapping mamapping) {
        this.saveOrUpdate(mamapping);
    }

   /* @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMamapping(Mamapping mamapping) {
        LambdaQueryWrapper<Mamapping> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}*/


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMamapping(String[] IDS) {
        List<String> list = Arrays.asList(IDS);
        LambdaQueryWrapper<Mamapping> wrapper = new LambdaQueryWrapper<>();
        baseMapper.delete(wrapper.in(Mamapping::getId,list));
    }
}
