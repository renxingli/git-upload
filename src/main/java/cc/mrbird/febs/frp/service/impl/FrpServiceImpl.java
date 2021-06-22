package cc.mrbird.febs.frp.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.frp.entity.Frp;
import cc.mrbird.febs.frp.mapper.FrpMapper;
import cc.mrbird.febs.frp.service.IFrpService;
import cc.mrbird.febs.material.entity.TMateria;
import cc.mrbird.febs.shop.entity.Shop;
import cc.mrbird.febs.stocks.entity.Stocks;
import cc.mrbird.febs.tampp.entity.Mamapping;
import cc.mrbird.febs.tampp.mapper.MamappingMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Service实现
 *
 * @author bjw
 * @date 2021-03-05 13:52:21
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FrpServiceImpl extends ServiceImpl<FrpMapper, Frp> implements IFrpService {

    private final FrpMapper frpMapper;

    private final MamappingMapper   mamappingMapper;

    @Override
    public IPage<Frp> findFrps(QueryRequest request, Frp frp) {
        LambdaQueryWrapper<Frp> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Frp> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }


    @Override
    public IPage<Frp> findFrp(QueryRequest request, Frp frp) {
        LambdaQueryWrapper<TMateria> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Frp> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countfrpDetail(frp));
        SortUtil.handlePageSort(request, page, "ID", FebsConstant.ORDER_ASC, false);
        return baseMapper.findtfrpDetailPage(page,frp);
    }


    @Override
    public List<Frp> findFrps(Frp frp) {
        QueryWrapper<Frp> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("ID",frp.getId());
        QueryWrapper<Mamapping>  maapqueryWrapper=new QueryWrapper<>();
        maapqueryWrapper.eq("FRP_ID",frp.getId());
        List<Mamapping> MAPP=mamappingMapper.selectList(maapqueryWrapper);
        List<String> mappList = new ArrayList<>();
        for(Mamapping ma:MAPP){
            mappList.add(ma.getBangdingId().toString());
        }
        String idza = "";
        for (int i = 0; i < mappList.size(); i++) {
            if (i == 0) {
                idza = mappList.get(i);
            } else {
                idza = idza + "," + mappList.get(i);
            }
        }
        String str[] = idza.split(",");
        queryWrapper.notIn("ID",str);
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    //取消绑定
    @Override
    public List<Frp> qxfindFrps(Frp frp) {
        QueryWrapper<Mamapping>  maapqueryWrapper=new QueryWrapper<>();
        maapqueryWrapper.eq("FRP_ID",frp.getId());
        List<Mamapping> MAPP=mamappingMapper.selectList(maapqueryWrapper);
        List<String> mappList = new ArrayList<>();
        for(Mamapping ma:MAPP){
            mappList.add(ma.getBangdingId().toString());
        }
        String idza = "";
        for (int i = 0; i < mappList.size(); i++) {
            if (i == 0) {
                idza = mappList.get(i);
            } else {
                idza = idza + "," + mappList.get(i);
            }
        }
        String str[] = idza.split(",");
        QueryWrapper<Frp>  queryWrapper=new QueryWrapper<>();
        queryWrapper.in("ID",str);
        // TODO 设置查询条件
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createFrp(Frp frp) {
        this.save(frp);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFrp(Frp frp) {
        this.saveOrUpdate(frp);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFrp(String[] IDS) {
        List<String> list = Arrays.asList(IDS);
        LambdaQueryWrapper<Frp> wrapper = new LambdaQueryWrapper<>();
        baseMapper.delete(wrapper.in(Frp::getId,list));
    }

    public Frp findfrpID(Integer id) {
        Frp frp = baseMapper.selectById(id);
        return frp;
    }

}
