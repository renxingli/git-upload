package cc.mrbird.febs.thesaurus.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.entity.Strings;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.stocks.entity.Stocks;
import cc.mrbird.febs.thesaurus.entity.Thesaurus;
import cc.mrbird.febs.thesaurus.service.IThesaurusService;
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
 * @date 2021-03-04 10:12:23
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("thesaurus")
public class ThesaurusController extends BaseController {

    private final IThesaurusService thesaurusService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "thesaurus")
    public String thesaurusIndex(){
        return FebsUtil.view("thesaurus/thesaurus");
    }

   /* @GetMapping("thesaurus")
    @ResponseBody
    @RequiresPermissions("thesaurus:list")
    public FebsResponse getAllThesauruss(Thesaurus thesaurus) {
        return new FebsResponse().success().data(thesaurusService.findThesauruss(thesaurus));
    }*/

    @GetMapping("list")
    public FebsResponse stocksList(QueryRequest request, Thesaurus thesaurus) {
        Map<String, Object> dataTable = getDataTable(this.thesaurusService.findThesauruss(request, thesaurus));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增Thesaurus", exceptionMessage = "新增Thesaurus失败")
    @PostMapping("thesaurus")
    @ResponseBody
    public FebsResponse addStocks(@Valid Thesaurus thesaurus) {
        this.thesaurusService.createThesaurus(thesaurus);
        return new FebsResponse().success();
    }


    @ControllerEndpoint(operation = "删除Thesaurus", exceptionMessage = "删除Thesaurus失败")
    @GetMapping("thesaurus/delete/{thesaurusIds}")
    @ResponseBody
    public FebsResponse deleteStocks(@PathVariable String thesaurusIds) {
        this.thesaurusService.deletethesaurus(StringUtils.split(thesaurusIds, Strings.COMMA));
        return new FebsResponse().success();
    }



    @ControllerEndpoint(operation = "修改Thesaurus", exceptionMessage = "修改Thesaurus失败")
    @PostMapping("thesaurus/update")
    @ResponseBody
    public FebsResponse updateThesaurus(Thesaurus thesaurus) {
        this.thesaurusService.updateThesaurus(thesaurus);
        return new FebsResponse().success();
    }



    @ControllerEndpoint(operation = "修改Thesaurus", exceptionMessage = "导出Excel失败")
    @PostMapping("thesaurus/excel")
    @ResponseBody
    @RequiresPermissions("thesaurus:export")
    public void export(QueryRequest queryRequest, Thesaurus thesaurus, HttpServletResponse response) {
        List<Thesaurus> thesauruss = this.thesaurusService.findThesauruss(queryRequest, thesaurus).getRecords();
        ExcelKit.$Export(Thesaurus.class, response).downXlsx(thesauruss, false);
    }
}
