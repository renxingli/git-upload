package cc.mrbird.febs.shop.controller;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.shop.service.Impl.ShopServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author A stubborn man
 * @create 2021/2/24 8:33
 * @Description 该类描述
 */

@Controller
@CrossOrigin
@RequiredArgsConstructor
public class ViewController {

    private final ShopServiceImpl shopService;

    /**
     * 跳转商品添加页面
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/shop/add")
    public String systemUserAdd(HttpServletRequest request) {
        request.getSession().removeAttribute("typeName");
        return FebsUtil.view("system/shop/shopAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX+"system/shop/type")
    public String systemType(){
        return FebsUtil.view("system/shop/type");
    }

    /**
     * 跳转商品查看详情页面
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/shop/detail/{commodityId}")
    public String systemUserDetail(@PathVariable Integer commodityId, Model model) {
        model.addAttribute("shop", shopService.findShop(commodityId));
        return FebsUtil.view("system/shop/shopDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/shop/update/{commodityId}")
    public String systemUserUpdate(@PathVariable Integer commodityId, Model model) {
        model.addAttribute("shop", shopService.findShop(commodityId));
        return FebsUtil.view("system/shop/shopUpdate");
    }

    /**
     * 添加规格
     *
     * @return
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/shop/add/gui/{commodityId}")
    public String addGuige(@PathVariable Integer commodityId, Model model) {
        model.addAttribute("commodityId", commodityId);
        return FebsUtil.view("system/shop/shopAddGui");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/shop/add/type")
    public String addGuige() {
        return FebsUtil.view("system/shop/shopAddType");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/shop/typeAdd")
    public String addType() {
        return FebsUtil.view("system/shop/typeAdd");
    }
}
