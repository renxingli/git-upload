package cc.mrbird.febs.helpcenter.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.helpcenter.entity.Helpcenter;
import cc.mrbird.febs.helpcenter.service.IHelpcenterService;
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
 * @date 2021-03-05 09:33:25
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class HelpcenterController extends BaseController {

    private final IHelpcenterService helpcenterService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "helpcenter")
    public String helpcenterIndex(){
        return FebsUtil.view("helpcenter/helpcenter");
    }

    @GetMapping("helpcenter")
    @ResponseBody
    @RequiresPermissions("helpcenter:list")
    public FebsResponse getAllHelpcenters(Helpcenter helpcenter) {
        return new FebsResponse().success().data(helpcenterService.findHelpcenters(helpcenter));
    }

    @GetMapping("helpcenter/list")
    @ResponseBody
    public FebsResponse helpcenterList(QueryRequest request, Helpcenter helpcenter) {
        Map<String, Object> dataTable = getDataTable(this.helpcenterService.findHelpcenters(request, helpcenter));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增Helpcenter", exceptionMessage = "新增Helpcenter失败")
    @PostMapping("helpcenter")
    @ResponseBody
    public FebsResponse addHelpcenter(@Valid Helpcenter helpcenter) {
        this.helpcenterService.createHelpcenter(helpcenter);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除Helpcenter", exceptionMessage = "删除Helpcenter失败")
    @GetMapping("helpcenter/delete")
    @ResponseBody
    @RequiresPermissions("helpcenter:delete")
    public FebsResponse deleteHelpcenter(Helpcenter helpcenter) {
        this.helpcenterService.deleteHelpcenter(helpcenter);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Helpcenter", exceptionMessage = "修改Helpcenter失败")
    @PostMapping("helpcenter/update")
    @ResponseBody
    public FebsResponse updateHelpcenter(Helpcenter helpcenter) {
        this.helpcenterService.updateHelpcenter(helpcenter);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Helpcenter", exceptionMessage = "导出Excel失败")
    @PostMapping("helpcenter/excel")
    @ResponseBody
    @RequiresPermissions("helpcenter:export")
    public void export(QueryRequest queryRequest, Helpcenter helpcenter, HttpServletResponse response) {
        List<Helpcenter> helpcenters = this.helpcenterService.findHelpcenters(queryRequest, helpcenter).getRecords();
        ExcelKit.$Export(Helpcenter.class, response).downXlsx(helpcenters, false);
    }

    @GetMapping("/help/delete/{helpcenterIds}")
    @ResponseBody
    public FebsResponse newsDelete(@PathVariable String[] helpcenterIds){
        helpcenterService.deleteHelps(helpcenterIds);
        return new FebsResponse().success();
    }
}
