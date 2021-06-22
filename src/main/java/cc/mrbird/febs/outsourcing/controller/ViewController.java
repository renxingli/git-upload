package cc.mrbird.febs.outsourcing.controller;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.outsourcing.entity.Outsourcing;
import cc.mrbird.febs.outsourcing.service.impl.OutsourcingServiceImpl;
import cc.mrbird.febs.shop.service.Impl.ShopServiceImpl;
import cc.mrbird.febs.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author A stubborn man
 * @create 2021/2/24 8:33
 * @Description 该类描述
 */

@Controller("out")
@CrossOrigin
@RequiredArgsConstructor
public class ViewController {

    private final OutsourcingServiceImpl outsourcingService;

    /**
     * 跳转商品添加页面
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/outsourcing/add")
    public String systemUserAdd() {
        return FebsUtil.view("system/outsourcing/outsourcingAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX+"system/outsourcing/detail/{outsourcingId}")
    public String outsourcingDetail(@PathVariable Integer outsourcingId,Model model){
        Outsourcing outsourcing = new Outsourcing();
        outsourcing.setOutsourcingId(outsourcingId);
        Outsourcing out = outsourcingService.getBaseMapper().selectById(outsourcingId);
        model.addAttribute("out",out);
        return FebsUtil.view("system/outsourcing/outsourcingDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX+"system/outsourcing/update/{outsourcingId}")
    public String outsourcingUpdate(@PathVariable Integer outsourcingId,Model model){
        Outsourcing outsourcing = new Outsourcing();
        outsourcing.setOutsourcingId(outsourcingId);
        Outsourcing out = outsourcingService.getBaseMapper().selectById(outsourcingId);
        model.addAttribute("out",out);
        return FebsUtil.view("system/outsourcing/outsourcingUpdate");
    }

}
