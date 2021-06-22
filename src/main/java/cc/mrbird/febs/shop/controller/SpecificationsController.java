package cc.mrbird.febs.shop.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.entity.Strings;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.shop.entity.Specifications;
import cc.mrbird.febs.shop.service.ISpecificationsService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author weiZiHao
 * @date 2021-03-02 10:56:43
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class SpecificationsController extends BaseController {

    private final ISpecificationsService specificationsService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "specifications")
    public String specificationsIndex() {
        return FebsUtil.view("specifications/specifications");
    }

    @GetMapping("specifications")
    @ResponseBody
    public FebsResponse getAllSpecificationss(Specifications specifications) {
        return new FebsResponse().success().data(specificationsService.findSpecificationss(specifications));
    }

    @GetMapping("specifications/list")
    @ResponseBody
    public FebsResponse specificationsList(QueryRequest request, Specifications specifications) {
        Map<String, Object> dataTable = getDataTable(this.specificationsService.findSpecificationss(request, specifications));
        return new FebsResponse().success().data(dataTable);
    }

    @GetMapping("specificationsAdd/list")
    @ResponseBody
    public FebsResponse specificationsList2(QueryRequest queryRequest, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object rxl = session.getAttribute("typeName");
        if(rxl!=null){
            String rxl1 = (String) rxl;
            String[] split = rxl1.split(",");
            List<String> strings = Arrays.asList(split);
            List<Specifications> list = new ArrayList<>();
            for(int i=0;i<strings.size();i++){
                Specifications specifications = new Specifications();
                specifications.setSpecificationsName(strings.get(i));
                specifications.setCommodityId(-1);
                list.add(i,specifications);
            }
            Specifications specifications = new Specifications();
            IPage<Specifications> specificationss = this.specificationsService.findSpecificationss(queryRequest, specifications);
            specificationss.setRecords(list);
            specificationss.setTotal(list.size());
            Map<String, Object> dataTable = getDataTable(specificationss);
            return new FebsResponse().success().data(dataTable);
        }else{
            Specifications specifications = new Specifications();
            specifications.setCommodityId(-1);
            IPage<Specifications> specificationss = this.specificationsService.findSpecificationss(queryRequest, specifications);
            specificationss.setTotal(0);
            Map<String, Object> dataTable = getDataTable(specificationss);
            return new FebsResponse().success().data(dataTable);
        }
    }

    @ControllerEndpoint(operation = "新增Specifications", exceptionMessage = "新增Specifications失败")
    @PostMapping("specifications/add")
    @ResponseBody
    public FebsResponse addSpecifications(@Valid Specifications specifications) {
        this.specificationsService.createSpecifications(specifications);
        return new FebsResponse().success();
    }


    @ControllerEndpoint(operation = "删除Specifications", exceptionMessage = "删除Specifications失败")
    @GetMapping("specifications/delete")
    @ResponseBody
    public FebsResponse deleteSpecifications(Specifications specifications) {
        this.specificationsService.deleteSpecifications(specifications);
        return new FebsResponse().success();
    }
    @ControllerEndpoint(operation = "删除Specifications", exceptionMessage = "删除Specifications失败")
    @GetMapping("specifications/deleteSession")
    @ResponseBody
    public FebsResponse deleteSpecifications1(Specifications specifications,HttpServletRequest request) {
        HttpSession session = request.getSession();
        String typeName = (String) session.getAttribute("typeName");
        String[] split = typeName.split(",");
        List<String> list = new ArrayList<String>(Arrays.asList(split));
        list.remove(specifications.getSpecificationsName());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(',');
            }
        }
        session.removeAttribute("typeName");
        session.setAttribute("typeName",sb.toString()+",");
        this.specificationsService.deleteSpecifications(specifications);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Specifications", exceptionMessage = "修改Specifications失败")
    @PostMapping("specifications/update")
    @ResponseBody
    public FebsResponse updateSpecifications(Specifications specifications) {
        this.specificationsService.updateSpecifications(specifications);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Specifications", exceptionMessage = "导出Excel失败")
    @PostMapping("specifications/excel")
    @ResponseBody
    public void export(QueryRequest queryRequest, Specifications specifications, HttpServletResponse response) {
        List<Specifications> specificationss = this.specificationsService.findSpecificationss(queryRequest, specifications).getRecords();
        ExcelKit.$Export(Specifications.class, response).downXlsx(specificationss, false);
    }
}
