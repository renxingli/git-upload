package cc.mrbird.febs.stocks.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.entity.Strings;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.material.entity.TMateria;
import cc.mrbird.febs.stocks.entity.Stocks;
import cc.mrbird.febs.stocks.service.IStocksService;
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
 * @date 2021-03-02 16:09:27
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("stocks")
public class StocksController extends BaseController {

    private final IStocksService stocksService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "stocks")
    public String stocksIndex(){
        return FebsUtil.view("stocks/stocks");
    }



    @GetMapping("list")
    public FebsResponse stocksList(QueryRequest request, Stocks stocks) {
        Map<String, Object> dataTable = getDataTable(this.stocksService.findStocks(request, stocks));
        return new FebsResponse().success().data(dataTable);
    }
    @ControllerEndpoint(operation = "新增Stocks", exceptionMessage = "新增Stocks失败")
    @PostMapping("stocks")
    @ResponseBody
   // @RequiresPermissions("stocks:add")
    public FebsResponse addStocks(@Valid Stocks stocks) {
        this.stocksService.createStocks(stocks);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除Stocks", exceptionMessage = "删除Stocks失败")
    @GetMapping("stocks/delete/{rawmaterialIds}")
    @ResponseBody
    public FebsResponse deleteStocks(@PathVariable String rawmaterialIds) {
        this.stocksService.deleteStocks(StringUtils.split(rawmaterialIds, Strings.COMMA));
        return new FebsResponse().success();
    }




    @ControllerEndpoint(operation = "修改Stocks", exceptionMessage = "修改Stocks失败")
    @PostMapping("stocks/update")
    @ResponseBody
    public FebsResponse updateStocks(Stocks stocks) {
        this.stocksService.updateStocks(stocks);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Stocks", exceptionMessage = "导出Excel失败")
    @PostMapping("stocks/excel")
    @ResponseBody
    @RequiresPermissions("stocks:export")
    public void export(QueryRequest queryRequest, Stocks stocks, HttpServletResponse response) {
        List<Stocks> stockss = this.stocksService.findStockss(queryRequest, stocks).getRecords();
        ExcelKit.$Export(Stocks.class, response).downXlsx(stockss, false);
    }
}
