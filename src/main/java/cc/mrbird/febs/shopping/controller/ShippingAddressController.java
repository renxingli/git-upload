package cc.mrbird.febs.shopping.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.shopping.entity.ShippingAddress;
import cc.mrbird.febs.shopping.service.IShippingAddressService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 *  Controller
 *
 * @author weiZiHao
 * @date 2021-03-02 14:05:22
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class ShippingAddressController extends BaseController {

    private final IShippingAddressService shippingAddressService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "shippingAddress")
    public String shippingAddressIndex(){
        return FebsUtil.view("shippingAddress/shippingAddress");
    }

    @GetMapping("shippingAddress")
    @ResponseBody
    @RequiresPermissions("shippingAddress:list")
    public FebsResponse getAllShippingAddresss(ShippingAddress shippingAddress) {
        return new FebsResponse().success().data(shippingAddressService.findShippingAddresss(shippingAddress));
    }

    @GetMapping("shippingAddress/list")
    @ResponseBody
    public FebsResponse shippingAddressList(QueryRequest request, ShippingAddress shippingAddress) {
        Map<String, Object> dataTable = getDataTable(this.shippingAddressService.findShippingAddresss(request, shippingAddress));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增ShippingAddress", exceptionMessage = "新增ShippingAddress失败")
    @PostMapping("shippingAddress")
    @ResponseBody
    @RequiresPermissions("shippingAddress:add")
    public FebsResponse addShippingAddress(@Valid ShippingAddress shippingAddress) {
        this.shippingAddressService.createShippingAddress(shippingAddress);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除ShippingAddress", exceptionMessage = "删除ShippingAddress失败")
    @GetMapping("shippingAddress/delete")
    @ResponseBody
    @RequiresPermissions("shippingAddress:delete")
    public FebsResponse deleteShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddressService.deleteShippingAddress(shippingAddress);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ShippingAddress", exceptionMessage = "修改ShippingAddress失败")
    @PostMapping("shippingAddress/update")
    @ResponseBody
    @RequiresPermissions("shippingAddress:update")
    public FebsResponse updateShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddressService.updateShippingAddress(shippingAddress);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ShippingAddress", exceptionMessage = "导出Excel失败")
    @PostMapping("shippingAddress/excel")
    @ResponseBody
    @RequiresPermissions("shippingAddress:export")
    public void export(QueryRequest queryRequest, ShippingAddress shippingAddress, HttpServletResponse response) {
        List<ShippingAddress> shippingAddresss = this.shippingAddressService.findShippingAddresss(queryRequest, shippingAddress).getRecords();
        ExcelKit.$Export(ShippingAddress.class, response).downXlsx(shippingAddresss, false);
    }

    /**
     * 修改状态
     * */

    @GetMapping("update/status/{shippingAddressId}/{stateId}")
    @ResponseBody
    public FebsResponse updateStatus(@PathVariable Integer shippingAddressId,@PathVariable Integer stateId){
        QueryWrapper<ShippingAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("SHIPPING_ADDRESS_ID",shippingAddressId);
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setStateId(stateId);
        this.shippingAddressService.getBaseMapper().update(shippingAddress, queryWrapper);
        return new FebsResponse().success();
    }
}
