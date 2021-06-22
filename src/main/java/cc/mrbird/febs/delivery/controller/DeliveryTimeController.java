package cc.mrbird.febs.delivery.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.delivery.entity.DeliveryTime;
import cc.mrbird.febs.delivery.service.IDeliveryTimeService;
import cc.mrbird.febs.delivery.service.impl.DeliveryTimeServiceImpl;
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
 * @date 2021-03-05 11:38:07
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class DeliveryTimeController extends BaseController {

    private final DeliveryTimeServiceImpl deliveryTimeService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "deliveryTime")
    public String deliveryTimeIndex(){
        return FebsUtil.view("deliveryTime/deliveryTime");
    }

    @GetMapping("deliveryTime")
    @ResponseBody
    @RequiresPermissions("deliveryTime:list")
    public FebsResponse getAllDeliveryTimes(DeliveryTime deliveryTime) {
        return new FebsResponse().success().data(deliveryTimeService.findDeliveryTimes(deliveryTime));
    }

    @GetMapping("deliveryTime/list")
    @ResponseBody
    public FebsResponse deliveryTimeList(QueryRequest request, DeliveryTime deliveryTime) {
        Map<String, Object> dataTable = getDataTable(this.deliveryTimeService.findDeliveryTimes(request, deliveryTime));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增DeliveryTime", exceptionMessage = "新增DeliveryTime失败")
    @PostMapping("deliveryTime")
    @ResponseBody
    public FebsResponse addDeliveryTime(@Valid DeliveryTime deliveryTime) {
        this.deliveryTimeService.createDeliveryTime(deliveryTime);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除DeliveryTime", exceptionMessage = "删除DeliveryTime失败")
    @GetMapping("deliveryTime/delete/{deliveryTimeIds}")
    @ResponseBody
    public FebsResponse deleteDeliveryTime(@PathVariable String[] deliveryTimeIds) {
        this.deliveryTimeService.deleteDelivers(deliveryTimeIds);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改DeliveryTime", exceptionMessage = "修改DeliveryTime失败")
    @PostMapping("deliveryTime/update")
    @ResponseBody
    public FebsResponse updateDeliveryTime(DeliveryTime deliveryTime) {
        this.deliveryTimeService.updateDeliveryTime(deliveryTime);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改DeliveryTime", exceptionMessage = "导出Excel失败")
    @GetMapping("deliveryTime/excel")
    @ResponseBody
    public void export(QueryRequest queryRequest, DeliveryTime deliveryTime, HttpServletResponse response) {
        List<DeliveryTime> deliveryTimes = this.deliveryTimeService.findDeliveryTimes(queryRequest, deliveryTime).getRecords();
        ExcelKit.$Export(DeliveryTime.class, response).downXlsx(deliveryTimes, false);
    }


}
