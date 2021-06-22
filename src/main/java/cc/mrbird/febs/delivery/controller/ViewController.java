package cc.mrbird.febs.delivery.controller;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.delivery.entity.DeliveryTime;
import cc.mrbird.febs.delivery.service.impl.DeliveryTimeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.poi.util.Internal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;

/**
 * @author A stubborn man
 * @create 2021/3/5 11:44
 * @Description
 */

@Controller("deliveryTimeView")
@CrossOrigin
@RequiredArgsConstructor
public class ViewController {

    private final DeliveryTimeServiceImpl deliveryTimeService;
    /**
     * 跳转交货时间新增页面
     * */
    @GetMapping(FebsConstant.VIEW_PREFIX+"system/delivery/add")
    public String deliveryAdd(){
        return FebsUtil.view("system/deliveryTime/deliveryTimeAdd");
    }

    /**
     * 跳转查看详情页面
     * */

    @GetMapping(FebsConstant.VIEW_PREFIX+"system/delivery/detail/{deliveryTimeId}")
    public String deliveryDetail(Model model, @PathVariable Integer deliveryTimeId){
        DeliveryTime deliveryTime = deliveryTimeService.deliveryDetail(deliveryTimeId);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = sf.format(deliveryTime.getCreateTime());
        String format2 = sf.format(deliveryTime.getModifyTime());
        deliveryTime.setTime1(format1);
        deliveryTime.setTime2(format2);
        model.addAttribute("delivery",deliveryTime);
        return FebsUtil.view("system/deliveryTime/deliveryDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX+"system/delivery/update/{deliveryTimeId}")
    public String deliveryUpdate(Model model, @PathVariable Integer deliveryTimeId){
        DeliveryTime deliveryTime = deliveryTimeService.deliveryDetail(deliveryTimeId);
        model.addAttribute("delivery",deliveryTime);
        return FebsUtil.view("system/deliveryTime/deliveryUpdate");
    }
}
