package cc.mrbird.febs.shop.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.entity.Strings;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.RundomSerial;
import cc.mrbird.febs.shop.entity.Shop;
import cc.mrbird.febs.shop.entity.Specifications;
import cc.mrbird.febs.shop.entity.Type;
import cc.mrbird.febs.shop.mapper.SpecificationsMapper;
import cc.mrbird.febs.shop.service.IShopService;
import cc.mrbird.febs.shop.service.Impl.ShopTypeServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *  Controller
 *
 * @author rxl
 * @date 2021-02-23 14:54:52
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("shop")
public class ShopController extends BaseController {

    private final IShopService shopService;
    private final SpecificationsMapper specificationsMapper;
    private final ShopTypeServiceImpl shopTypeService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "shop")
    public String shopIndex(){
        return FebsUtil.view("shop/shop");
    }

    @GetMapping("shop")
    @ResponseBody
    public FebsResponse getAllShops(Integer commodityId) {
        return new FebsResponse().success().data(shopService.findShop(commodityId));
    }

    @GetMapping("list")
    public FebsResponse shopList(QueryRequest request, Shop shop) {
        Map<String, Object> dataTable = getDataTable(this.shopService.findShops(request, shop));
        List<Shop> rows =(List<Shop>) dataTable.get("rows");
        rows.forEach(dao->{
            QueryWrapper<Specifications> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.select("SPECIFICATIONS_NAME","SPECIFICATIONS_ID").eq("COMMODITY_ID",dao.getCommodityId());
            List<Specifications> specifications = specificationsMapper.selectList(queryWrapper1);
            if(specifications.size()!=0){
                String a= "";
                String b = "";
                for(int i = 0;i<specifications.size();i++){
                    String specificationsName = specifications.get(i).getSpecificationsName();
                    Integer specificationsId = specifications.get(i).getSpecificationsId();
                    a+= specificationsName+",";
                    b+= specificationsId+",";
                }
                dao.setSpecificationsName(a);
                dao.setSpecificationsIds(b);
            }
        });
        return new FebsResponse().success().data(dataTable);
    }

    @GetMapping("type/list")
    public FebsResponse typeList(QueryRequest request, Type type){
        Map<String, Object> dataTable = getDataTable(this.shopTypeService.findType(request, type));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增Shop", exceptionMessage = "新增Shop失败")
    @PostMapping("shop")
    @ResponseBody
    public FebsResponse addShop(@Valid Shop shop, HttpServletRequest request) {
        System.out.println("shop:"+shop);
        String  commodityNumber= RundomSerial.genRandomNum();
        shop.setCommodityNumber(commodityNumber);
        this.shopService.createShop(shop);
        Integer a = shop.getCommodityId();
        HttpSession session = request.getSession();
        String typeName = (String)session.getAttribute("typeName");
        if(typeName!=null) {
            String[] split = typeName.split(",");
            List<String> strings = Arrays.asList(split);
            for (int i = 0; i < strings.size(); i++) {
                Specifications specifications1 = new Specifications();
                specifications1.setSpecificationsName(strings.get(i));
                specifications1.setCommodityId(a);
                this.specificationsMapper.insert(specifications1);
            }
        }else{
            return new FebsResponse().success().message("添加shop失败，keenng1");
        }
        ShopTypeController.delSession(request);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除Shop", exceptionMessage = "删除Shop失败")
    @GetMapping("shop/delete/{shopIds}")
    @ResponseBody
    public FebsResponse deleteShop(@PathVariable String shopIds) {
        this.shopService.deleteShop(StringUtils.split(shopIds, Strings.COMMA));
        this.shopService.deleteGG(StringUtils.split(shopIds, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Shop", exceptionMessage = "修改Shop失败")
    @PostMapping("shop/update")
    @ResponseBody
    public FebsResponse updateShop(Shop shop) {
        this.shopService.updateShop(shop);
        Specifications specifications = new Specifications();
//        if(shop.getSpecificationsIds()==null||shop.getSpecificationsIds().isEmpty()){
//            specifications.setSpecificationsName(shop.getSpecificationsName());
//            specifications.setCommodityId(shop.getCommodityId());
//            specificationsMapper.insert(specifications);
//        }else{
//            specifications.setSpecificationsName(shop.getSpecificationsName());
//            specifications.setCommodityId(shop.getCommodityId());
//            specifications.setSpecificationsId(Integer.parseInt(shop.getSpecificationsIds().replace(",","")));
//            specificationsMapper.updateById(specifications);
//        }
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Shop", exceptionMessage = "导出Excel失败")
    @GetMapping("shop/excel")
    @ResponseBody
    public void export(QueryRequest queryRequest, Shop shop, HttpServletResponse response) {
        IPage<Shop> shops = shopService.findShops(queryRequest, shop);
        Map<String, Object> dataTable = getDataTable(shops);
        List<Shop> rows =(List<Shop>) dataTable.get("rows");
        rows.forEach(dao->{
            QueryWrapper<Specifications> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.select("SPECIFICATIONS_NAME","SPECIFICATIONS_ID").eq("COMMODITY_ID",dao.getCommodityId());
            List<Specifications> specifications = specificationsMapper.selectList(queryWrapper1);
            if(specifications.size()!=0){
                String a= "";
                String b = "";
                for(int i = 0;i<specifications.size();i++){
                    String specificationsName = specifications.get(i).getSpecificationsName();
                    Integer specificationsId = specifications.get(i).getSpecificationsId();
                    a+= specificationsName+",";
                    b+= specificationsId+",";
                }
                dao.setSpecificationsName(a);
                dao.setSpecificationsIds(b);
            }
        });
        ExcelKit.$Export(Shop.class, response)
                .downXlsx(shops.getRecords(), false);
    }

}
