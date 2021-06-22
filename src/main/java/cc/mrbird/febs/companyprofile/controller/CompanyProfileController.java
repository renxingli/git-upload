package cc.mrbird.febs.companyprofile.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.companyprofile.entity.CompanyProfile;
import cc.mrbird.febs.companyprofile.service.ICompanyProfileService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
 * @date 2021-03-03 16:55:22
 * 公司介绍操作类
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class CompanyProfileController extends BaseController {

    private final ICompanyProfileService companyProfileService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "companyProfile")
    public String companyProfileIndex(){
        return FebsUtil.view("companyProfile/companyProfile");
    }

    @GetMapping("companyProfile")
    @ResponseBody
    @RequiresPermissions("companyProfile:list")
    public FebsResponse getAllCompanyProfiles(CompanyProfile companyProfile) {
        return new FebsResponse().success().data(companyProfileService.findCompanyProfiles(companyProfile));
    }

    @GetMapping("companyProfile/list")
    @ResponseBody
    @RequiresPermissions("companyProfile:list")
    public FebsResponse companyProfileList(QueryRequest request, CompanyProfile companyProfile) {
        Map<String, Object> dataTable = getDataTable(this.companyProfileService.findCompanyProfiles(request, companyProfile));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增CompanyProfile", exceptionMessage = "新增CompanyProfile失败")
    @PostMapping("companyProfile")
    @ResponseBody
    @RequiresPermissions("companyProfile:add")
    public FebsResponse addCompanyProfile(@Valid CompanyProfile companyProfile) {
        this.companyProfileService.createCompanyProfile(companyProfile);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除CompanyProfile", exceptionMessage = "删除CompanyProfile失败")
    @GetMapping("companyProfile/delete")
    @ResponseBody
    @RequiresPermissions("companyProfile:delete")
    public FebsResponse deleteCompanyProfile(CompanyProfile companyProfile) {
        this.companyProfileService.deleteCompanyProfile(companyProfile);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改CompanyProfile", exceptionMessage = "修改CompanyProfile失败")
    @PostMapping("companyProfile/update")
    @ResponseBody
    @RequiresPermissions("companyProfile:update")
    public FebsResponse updateCompanyProfile(CompanyProfile companyProfile) {
        this.companyProfileService.updateCompanyProfile(companyProfile);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改CompanyProfile", exceptionMessage = "导出Excel失败")
    @PostMapping("companyProfile/excel")
    @ResponseBody
    @RequiresPermissions("companyProfile:export")
    public void export(QueryRequest queryRequest, CompanyProfile companyProfile, HttpServletResponse response) {
        List<CompanyProfile> companyProfiles = this.companyProfileService.findCompanyProfiles(queryRequest, companyProfile).getRecords();
        ExcelKit.$Export(CompanyProfile.class, response).downXlsx(companyProfiles, false);
    }

    @PostMapping("company/update")
    @ResponseBody
    public FebsResponse updateCompany(@Valid CompanyProfile companyProfile){
        this.companyProfileService.updateCompanyProfile(companyProfile);
        return new FebsResponse().success();
    }
}
