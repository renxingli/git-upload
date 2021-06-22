package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Region;
import cc.mrbird.febs.system.entity.User;
import cc.mrbird.febs.system.service.IRegionService;
import cc.mrbird.febs.system.service.IUserService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * 全国地址 Controller
 *
 * @author weiZiHao
 * @date 2021-03-08 10:04:00
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class RegionController extends BaseController {

    private final IRegionService regionService;

    private final IUserService userService;

    /**
     * 输出中心管理
     *
     * @return
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/factory")
    public String factoryIndex() {
        return FebsUtil.view("system/factory/factory");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/factory/update/{userId}")
    public String factoryUpdate(@PathVariable Long userId, Model model) {
        User user = userService.getById(userId);
        model.addAttribute("user", user);
        return FebsUtil.view("system/factory/factoryUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/factory/add")
    public String factoryAdd() {
        return FebsUtil.view("system/factory/factoryAdd");
    }

    /**
     * @return
     */

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/region")
    public String regionIndex() {
        return FebsUtil.view("system/region/region");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/region/update/{id}")
    public String region(@PathVariable Integer id, Model model) {
        Region region = regionService.getById(id);
        model.addAttribute("region", region);
        return FebsUtil.view("system/region/regionUpdate");
    }

    @GetMapping("region")
    @ResponseBody
    @RequiresPermissions("region:list")
    public FebsResponse getAllRegions(Region region) {
        return new FebsResponse().success().data(regionService.findRegions(region));
    }

    @GetMapping("region/list")
    @ResponseBody
    @RequiresPermissions("region:list")
    public FebsResponse regionList(QueryRequest request, Region region) {
        Map<String, Object> dataTable = getDataTable(this.regionService.findRegions(request, region));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增Region", exceptionMessage = "新增Region失败")
    @PostMapping("region")
    @ResponseBody
    @RequiresPermissions("region:add")
    public FebsResponse addRegion(@Valid Region region) {
        this.regionService.createRegion(region);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除Region", exceptionMessage = "删除Region失败")
    @GetMapping("region/delete")
    @ResponseBody
    @RequiresPermissions("region:delete")
    public FebsResponse deleteRegion(Region region) {
        this.regionService.deleteRegion(region);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Region", exceptionMessage = "修改Region失败")
    @PostMapping("region/update")
    @ResponseBody
    public FebsResponse updateRegion(Region region) {
        this.regionService.updateRegion(region);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Region", exceptionMessage = "导出Excel失败")
    @PostMapping("region/excel")
    @ResponseBody
    @RequiresPermissions("region:export")
    public void export(QueryRequest queryRequest, Region region, HttpServletResponse response) {
        List<Region> regions = this.regionService.findRegions(queryRequest, region).getRecords();
        ExcelKit.$Export(Region.class, response).downXlsx(regions, false);
    }
}
