package cc.mrbird.febs.shop.service.Impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.shop.entity.Shop;
import cc.mrbird.febs.shop.entity.Specifications;
import cc.mrbird.febs.shop.entity.Type;
import cc.mrbird.febs.shop.mapper.ShopMapper;
import cc.mrbird.febs.shop.mapper.SpecificationsMapper;
import cc.mrbird.febs.shop.mapper.TypeMapper;
import cc.mrbird.febs.shop.service.IShopService;
import cc.mrbird.febs.system.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
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
 * @author rxl
 * @date 2021-02-23 14:54:52
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    private final TypeMapper typeMapper;
    private final SpecificationsMapper specificationsMapper;

    @Override
    public IPage<Shop> findShops(QueryRequest request, Shop shop) {
        if (StringUtils.isNotBlank(shop.getCreateTimeFrom()) &&
                StringUtils.equals(shop.getCreateTimeFrom(), shop.getCreateTimeTo())) {
            shop.setCreateTimeFrom(shop.getCreateTimeFrom() + FebsConstant.DAY_START_PATTERN_SUFFIX);
            shop.setCreateTimeTo(shop.getCreateTimeTo() + FebsConstant.DAY_END_PATTERN_SUFFIX);
        }
        Page<Shop> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countShopDetail(shop));
        SortUtil.handlePageSort(request, page, "commodityId", FebsConstant.ORDER_ASC, true);
        return baseMapper.findShopDetailPage(page, shop);
    }


    @Override
    public Shop findShop(Integer commodityId) {
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("TYPE_ID").eq("COMMODITY_ID",commodityId);
        Integer types = baseMapper.selectCount(queryWrapper);
        Shop shop = baseMapper.selectById(commodityId);
        if (types!=0 && shop.getTypeId()!=0){
            List<Shop> shops = baseMapper.selectList(queryWrapper);
                QueryWrapper<Type> queryWrapper1 = new QueryWrapper<>();
                Integer typeId = shops.get(0).getTypeId();
                queryWrapper1.select("TYPE_NAME").eq("TYPE_ID", typeId);
                List<Type> types1 = typeMapper.selectList(queryWrapper1);
                shop.setShopType(types1.get(0).getTypeName());
        }else{
            shop.setShopType(Type.TYPE_NAME);
        }

        QueryWrapper<Specifications> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.select("SPECIFICATIONS_NAME","SPECIFICATIONS_ID").eq("COMMODITY_ID",commodityId);
        List<Specifications> specifications = specificationsMapper.selectList(queryWrapper2);
        if(specifications.size()!=0){
            String a= "";
            String b = "";
            for(int i = 0;i<specifications.size();i++){
                String specificationsName = specifications.get(i).getSpecificationsName();
                Integer specificationsId = specifications.get(i).getSpecificationsId();
                a+= specificationsName+",";
                b+= specificationsId+",";
            }
            shop.setSpecificationsName(a);
            shop.setSpecificationsIds(b);
        }else{
            shop.setSpecificationsName("还未定义规格");
        }
		return shop;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createShop(Shop shop) {
        this.save(shop);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateShop(Shop shop) {
        this.saveOrUpdate(shop);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteShop(String[] shopIds) {
        List<String> list = Arrays.asList(shopIds);
        LambdaQueryWrapper<Shop> wrapper = new LambdaQueryWrapper<>();
        baseMapper.delete(wrapper.in(Shop::getCommodityId,list));
	}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGG(String[] shopIds) {
        List<String> list = Arrays.asList(shopIds);
        LambdaQueryWrapper<Specifications> wrapper = new LambdaQueryWrapper<>();
        specificationsMapper.delete(wrapper.in(Specifications::getCommodityId,list));
    }
}
