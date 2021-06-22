package cc.mrbird.febs.frp.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.entity.Strings;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.frp.entity.Frp;
import cc.mrbird.febs.frp.service.IFrpService;
import cc.mrbird.febs.stocks.entity.Stocks;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 *  Controller
 *
 * @author bjw
 * @date 2021-03-05 13:52:21
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("frp")
public class FrpController extends BaseController {

    private final IFrpService frpService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "frp")
    public String frpIndex(){
        return FebsUtil.view("frp/frp");
    }


    @GetMapping("list")
    public FebsResponse frpList(QueryRequest request, Frp frp) {
        Map<String, Object> dataTable = getDataTable(this.frpService.findFrp(request, frp));
        return new FebsResponse().success().data(dataTable);
    }

    @GetMapping("grouplist/{iDd}")
    public FebsResponse grouplist(QueryRequest request, Frp frp,@PathVariable String iDd) {
        frp.setId(Long.parseLong(iDd));
        List<Frp> frps=this.frpService.findFrps(frp);
        return new FebsResponse().success().data(frps);
    }

    //取消绑定的数据
    @GetMapping("qxgrouplist/{iDd}")
    public FebsResponse qxgrouplist(QueryRequest request, Frp frp,@PathVariable String iDd) {
        frp.setId(Long.parseLong(iDd));
        List<Frp> frps=this.frpService.qxfindFrps(frp);
        return new FebsResponse().success().data(frps);
    }

    @ControllerEndpoint(operation = "新增Frp", exceptionMessage = "新增Frp失败")
    @PostMapping("frp")
    @ResponseBody
   // @RequiresPermissions("frp:add")
    public FebsResponse addFrp(@Valid Frp frp) {
        this.frpService.createFrp(frp);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除Frp", exceptionMessage = "删除Frp失败")
    @GetMapping("frp/delete/{IDS}")
    @ResponseBody
    public FebsResponse deleteFrp(@PathVariable String IDS) {
        this.frpService.deleteFrp(StringUtils.split(IDS, Strings.COMMA));
        return new FebsResponse().success();
    }


    @ControllerEndpoint(operation = "修改Frp", exceptionMessage = "修改Frp失败")
    @PostMapping("frp/update")
    @ResponseBody
    public FebsResponse updateFrp(Frp frp) {
        this.frpService.updateFrp(frp);
        return new FebsResponse().success();
    }


    @ControllerEndpoint(operation = "修改Frp", exceptionMessage = "导出Excel失败")
    @PostMapping("frp/excel")
    @ResponseBody
    @RequiresPermissions("frp:export")
    public void export(QueryRequest queryRequest, Frp frp, HttpServletResponse response) {
        List<Frp> frps = this.frpService.findFrps(queryRequest, frp).getRecords();
        ExcelKit.$Export(Frp.class, response).downXlsx(frps, false);
    }
}
