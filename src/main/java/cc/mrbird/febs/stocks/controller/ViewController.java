package cc.mrbird.febs.stocks.controller;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.shop.service.Impl.ShopServiceImpl;
import cc.mrbird.febs.stocks.service.impl.StocksServiceImpl;
import lombok.RequiredArgsConstructor;
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

@Controller("stocksView")
@CrossOrigin
@RequiredArgsConstructor
public class ViewController {

    private final StocksServiceImpl stocksService;
    /**
     * 跳转添加页面
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/stocks/add")
    public String systemStocksAdd() {
        return FebsUtil.view("system/stocks/stocksAdd");
    }


    /**
     * 跳转修改页面
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/stocks/update/{rawmaterialId}")
    public String systemUserUpdate(@PathVariable Integer rawmaterialId, Model model) {
        model.addAttribute("stocks", stocksService.findStockID(rawmaterialId));
        return FebsUtil.view("system/stocks/stocksUpdate");

    }

}
