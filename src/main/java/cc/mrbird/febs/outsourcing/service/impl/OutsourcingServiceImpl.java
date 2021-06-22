package cc.mrbird.febs.outsourcing.service.impl;

import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.outsourcing.entity.Outsourcing;
import cc.mrbird.febs.outsourcing.mapper.OutsourcingMapper;
import cc.mrbird.febs.outsourcing.service.IOutsourcingService;
import cc.mrbird.febs.shop.entity.Shop;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 *  Service实现
 *
 * @author rxl
 * @date 2021-02-26 10:37:09
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OutsourcingServiceImpl extends ServiceImpl<OutsourcingMapper, Outsourcing> implements IOutsourcingService {

    private final OutsourcingMapper outsourcingMapper;

    @Override
    public IPage<Outsourcing> findOutsourcings(QueryRequest request, Outsourcing outsourcing) {
        LambdaQueryWrapper<Outsourcing> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Outsourcing> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Outsourcing> findOutsourcings(Outsourcing outsourcing) {
	    LambdaQueryWrapper<Outsourcing> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOutsourcing(Outsourcing outsourcing) {
        this.save(outsourcing);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOutsourcing(Outsourcing outsourcing) {
        this.saveOrUpdate(outsourcing);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOutsourcing(String[] outsourcings) {
        List<String> list = Arrays.asList(outsourcings);
        LambdaQueryWrapper<Outsourcing> wrapper = new LambdaQueryWrapper<>();
        baseMapper.delete(wrapper.in(Outsourcing::getOutsourcingId,list));
	    this.remove(wrapper);
	}

	@Override
	public FebsResponse AddOutsourcing(HttpServletRequest request){
        QueryWrapper<Outsourcing> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("OUTSOURCING_ID");
        Integer integer = outsourcingMapper.selectCount(queryWrapper);
        if(integer!=0){
            return new FebsResponse().fail().message("数据有且只能有一条");
        }else{
            String richText =  request.getParameter("richText");
            System.out.println(richText);
            Outsourcing outsourcing = new Outsourcing();
            outsourcing.setOutsourcingNews(richText);
            createOutsourcing(outsourcing);
            return new FebsResponse().success().message("数据添加成功").data(outsourcing);
        }
    }

}
