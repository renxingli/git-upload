package cc.mrbird.febs.merchant.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.merchant.entity.Merchant;
import cc.mrbird.febs.merchant.service.IMerchantService;
import cc.mrbird.febs.shopping.entity.ShippingAddress;
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
 * @author A stubborn man
 * @date 2021-03-08 10:35:20
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class MerchantController extends BaseController {

    private final IMerchantService merchantService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "/system/merchant")
    public String merchantIndex(){
        return FebsUtil.view("system/merchant/merchant");
    }

    @GetMapping("merchant")
    @ResponseBody
    public FebsResponse getAllMerchants(Merchant merchant) {
        return new FebsResponse().success().data(merchantService.findMerchants(merchant));
    }

    @GetMapping("merchant/list")
    @ResponseBody
    public FebsResponse merchantList(QueryRequest request, Merchant merchant) {
        System.out.println("a:"+merchant);
        Map<String, Object> dataTable = getDataTable(this.merchantService.findMerchants(request, merchant));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增Merchant", exceptionMessage = "新增Merchant失败")
    @PostMapping("merchant")
    @ResponseBody
    public FebsResponse addMerchant(@Valid Merchant merchant) {
        this.merchantService.createMerchant(merchant);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除Merchant", exceptionMessage = "删除Merchant失败")
    @GetMapping("merchant/delete")
    @ResponseBody
    public FebsResponse deleteMerchant(Merchant merchant) {
        this.merchantService.deleteMerchant(merchant);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Merchant", exceptionMessage = "修改Merchant失败")
    @GetMapping("merchant/update/{merchantId}/{stateId}")
    @ResponseBody
    public FebsResponse updateMerchant(@PathVariable Integer merchantId, @PathVariable Integer stateId) {
        QueryWrapper<Merchant> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("MERCHANT_ID",merchantId);
        Merchant merchant = new Merchant();
        merchant.setStateId(stateId);
        this.merchantService.getBaseMapper().update(merchant, queryWrapper);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Merchant", exceptionMessage = "导出Excel失败")
    @PostMapping("merchant/excel")
    @ResponseBody
    @RequiresPermissions("merchant:export")
    public void export(QueryRequest queryRequest, Merchant merchant, HttpServletResponse response) {
        List<Merchant> merchants = this.merchantService.findMerchants(queryRequest, merchant).getRecords();
        ExcelKit.$Export(Merchant.class, response).downXlsx(merchants, false);
    }
}
