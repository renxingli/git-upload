package cc.mrbird.febs.frp.controller;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.frp.service.impl.FrpServiceImpl;
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

@Controller("frpView")
@CrossOrigin
@RequiredArgsConstructor
public class ViewController {

    private final FrpServiceImpl frpService;
    /**
     * 跳转添加页面
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/frp/add")
    public String systemFrpAdd() {
        return FebsUtil.view("system/frp/frpAdd");
    }


 /**
     * 跳转修改页面
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/frp/update/{id}")
    public String systemUserUpdate(@PathVariable Integer id, Model model) {
        model.addAttribute("frp", frpService.findfrpID(id));
        return FebsUtil.view("system/frp/frpUpdate");

    }



    /**
     * 跳转绑定数据页面
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/frpbding/update/{id}")
    public String systembding(@PathVariable Integer id, Model model) {
        model.addAttribute("frpb", frpService.findfrpID(id));
        return FebsUtil.view("system/frp/frpbdingsj");

    }


    /**
     * 跳转取消绑定数据
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/frpbding/qxbd/{id}")
    public String systqxembding(@PathVariable Integer id, Model model) {
        model.addAttribute("frpqb", frpService.findfrpID(id));
        return FebsUtil.view("system/frp/frpqxbding");

    }

}
