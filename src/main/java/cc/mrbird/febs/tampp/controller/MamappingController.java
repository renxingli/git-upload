package cc.mrbird.febs.tampp.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.entity.Strings;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.frp.entity.Frp;
import cc.mrbird.febs.tampp.entity.Mamapping;
import cc.mrbird.febs.tampp.mapper.MamappingMapper;
import cc.mrbird.febs.tampp.service.IMamappingService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * @date 2021-03-06 14:02:49
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("mamapping")
public class MamappingController extends BaseController {

    private final IMamappingService mamappingService;
    private final MamappingMapper  mamappingMapper;
    @GetMapping(FebsConstant.VIEW_PREFIX + "mamapping")
    public String mamappingIndex(){
        return FebsUtil.view("mamapping/mamapping");
    }

    @GetMapping("mamapping")
    @ResponseBody
    @RequiresPermissions("mamapping:list")
    public FebsResponse getAllMamappings(Mamapping mamapping) {
        return new FebsResponse().success().data(mamappingService.findMamappings(mamapping));
    }

    @GetMapping("mamapping/list")
    @ResponseBody
    @RequiresPermissions("mamapping:list")
    public FebsResponse mamappingList(QueryRequest request, Mamapping mamapping) {
        Map<String, Object> dataTable = getDataTable(this.mamappingService.findMamappings(request, mamapping));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增Mamapping", exceptionMessage = "新增Mamapping失败")
    @PostMapping("mamapping")
    @ResponseBody
    @RequiresPermissions("mamapping:add")
    public FebsResponse addMamapping(@Valid Mamapping mamapping) {
        this.mamappingService.createMamapping(mamapping);
        return new FebsResponse().success();
    }

/*    @ControllerEndpoint(operation = "删除Mamapping", exceptionMessage = "删除Mamapping失败")
    @GetMapping("mamapping/delete")
    @ResponseBody
    @RequiresPermissions("mamapping:delete")
    public FebsResponse deleteMamapping(Mamapping mamapping) {
        this.mamappingService.deleteMamapping(mamapping);
        return new FebsResponse().success();
    }*/

    @ControllerEndpoint(operation = "绑定数据", exceptionMessage = "绑定数据失败")
    @GetMapping("mamapping/update/{id}/{bdiddata}")
    @ResponseBody
   // @RequiresPermissions("mamapping:update")
    public FebsResponse updateMamapping(@PathVariable String id,@PathVariable String bdiddata) {
        String str[] =bdiddata.split(",");
        Mamapping ma= new Mamapping();
        for(int i=0; i<str.length;i++){
            ma.setFrpId(Integer.parseInt(id));
            ma.setBangdingId(Integer.parseInt(str[i]));
            this.mamappingService.createMamapping(ma);
        }
        return new FebsResponse().success();
    }


    @ControllerEndpoint(operation = "取消绑定", exceptionMessage = "取消绑定失败")
    @GetMapping("mamapping/qxupdate/{id}/{bdiddata}")
    @ResponseBody
    // @RequiresPermissions("mamapping:update")
    public FebsResponse qxupdateMamapping(@PathVariable String id,@PathVariable String bdiddata) {
        String str[] =bdiddata.split(",");
        for(int i=0; i<str.length;i++){
            QueryWrapper<Mamapping> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("FRP_ID",Integer.parseInt(id));
            queryWrapper.eq("BANGDING_ID",Integer.parseInt(str[i]));
            Mamapping ma=this.mamappingMapper.selectList(queryWrapper).get(0);
            this.mamappingService.deleteMamapping(StringUtils.split(ma.getId().toString(),Strings.COMMA));
        }
        return new FebsResponse().success();
    }



    @ControllerEndpoint(operation = "修改Mamapping", exceptionMessage = "导出Excel失败")
    @PostMapping("mamapping/excel")
    @ResponseBody
    @RequiresPermissions("mamapping:export")
    public void export(QueryRequest queryRequest, Mamapping mamapping, HttpServletResponse response) {
        List<Mamapping> mamappings = this.mamappingService.findMamappings(queryRequest, mamapping).getRecords();
        ExcelKit.$Export(Mamapping.class, response).downXlsx(mamappings, false);
    }
}
