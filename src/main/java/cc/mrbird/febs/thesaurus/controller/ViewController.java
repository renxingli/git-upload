package cc.mrbird.febs.thesaurus.controller;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.stocks.service.impl.StocksServiceImpl;
import cc.mrbird.febs.thesaurus.entity.Thesaurus;
import cc.mrbird.febs.thesaurus.service.impl.ThesaurusServiceImpl;
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

@Controller("thesaurusView")
@CrossOrigin
@RequiredArgsConstructor
public class ViewController {

    private final ThesaurusServiceImpl thesaurusService;
    /**
     * 跳转添加页面
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/thesaurus/add")
    public String systemStocksAdd() {
        return FebsUtil.view("system/thesaurus/thesaurusAdd");
    }


    /**
     * 跳转修改页面
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/thesaurus/update/{thesaurusId}")
    public String systemUserUpdate(@PathVariable Integer thesaurusId, Model model) {
        model.addAttribute("thesaurus", thesaurusService.findthesaurusID(thesaurusId));
        return FebsUtil.view("system/thesaurus/thesaurusUpdate");

    }

}
