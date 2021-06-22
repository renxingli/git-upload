package cc.mrbird.febs.material.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.material.entity.TMateria;
import cc.mrbird.febs.material.service.ITMateriaService;
import cc.mrbird.febs.shop.entity.Shop;
import com.wuwenze.poi.ExcelKit;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 *  Controller
 *
 * @author bjw
 * @date 2021-03-01 11:31:45
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("tMateria")
public class TMateriaController extends BaseController {

    private final ITMateriaService tMateriaService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "tMateria")
    public String tMateriaIndex(){
        return FebsUtil.view("tMateria/tMateria");
    }

    @GetMapping("tMateria")
    @ResponseBody
    public FebsResponse getAllTMaterias(TMateria tMateria) {
        return new FebsResponse().success().data(tMateriaService.findTMaterias(tMateria));
    }

    @GetMapping("list")
    public FebsResponse shopList(QueryRequest request, TMateria tMateria) {
        Map<String, Object> dataTable = getDataTable(this.tMateriaService.findTMaterias(request, tMateria));
        return new FebsResponse().success().data(dataTable);
    }
    @GetMapping("tingyong/{materialId}")
    public FebsResponse tingyong(@NotBlank(message = "{required}") @PathVariable String materialId) {
        if (null==materialId||""==materialId) {
            throw new FebsException("ID不能为空");
        }
        TMateria  tma =new TMateria();
        tma.setMaterialId(Integer.parseInt(materialId));
        tma.setStatus("0");
        this.tMateriaService.updateTMateria(tma);
        return new FebsResponse().success();
    }
 /*   @GetMapping("tMateria/list")
    @ResponseBody
    @RequiresPermissions("tMateria:list")
    public FebsResponse tMateriaList(QueryRequest request, TMateria tMateria) {
        Map<String, Object> dataTable = getDataTable(this.tMateriaService.findTMaterias(request, tMateria));
        return new FebsResponse().success().data(dataTable);
    }*/

    @ControllerEndpoint(operation = "新增TMateria", exceptionMessage = "新增TMateria失败")
    @PostMapping("tMateria")
    @ResponseBody
    @RequiresPermissions("tMateria:add")
    public FebsResponse addTMateria(@Valid TMateria tMateria) {
        this.tMateriaService.createTMateria(tMateria);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除TMateria", exceptionMessage = "删除TMateria失败")
    @GetMapping("tMateria/delete")
    @ResponseBody
    @RequiresPermissions("tMateria:delete")
    public FebsResponse deleteTMateria(TMateria tMateria) {
        this.tMateriaService.deleteTMateria(tMateria);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改TMateria", exceptionMessage = "修改TMateria失败")
    @PostMapping("tMateria/update")
    @ResponseBody
    @RequiresPermissions("tMateria:update")
    public FebsResponse updateTMateria(TMateria tMateria) {
        this.tMateriaService.updateTMateria(tMateria);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改TMateria", exceptionMessage = "导出Excel失败")
    @PostMapping("tMateria/excel")
    @ResponseBody
    @RequiresPermissions("tMateria:export")
    public void export(QueryRequest queryRequest, TMateria tMateria, HttpServletResponse response) {
        List<TMateria> tMaterias = this.tMateriaService.findTMaterias(queryRequest, tMateria).getRecords();
        ExcelKit.$Export(TMateria.class, response).downXlsx(tMaterias, false);
    }
}
