package cc.mrbird.febs.helpcenter.controller;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.helpcenter.entity.Helpcenter;
import cc.mrbird.febs.helpcenter.service.impl.HelpcenterServiceImpl;
import cc.mrbird.febs.news.entity.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;

/**
 * @author A stubborn man
 * @create 2021/3/5 9:54
 * @Description
 */

@Controller("helpView")
@CrossOrigin
@RequiredArgsConstructor
public class ViewController {

    private final HelpcenterServiceImpl helpcenterService;
    /**
     * 跳转帮助中心增加页
     * */
    @GetMapping(FebsConstant.VIEW_PREFIX+"system/help/add")
    public String newsAdd(){
        return FebsUtil.view("system/helpcenter/helpcenterAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX+"system/help/detail/{helpcenterId}")
    public String newsDetail(@PathVariable Integer helpcenterId, Model model){
        Helpcenter help = helpcenterService.helpDetail(helpcenterId);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = sf.format(help.getCreateTime());
        String format2 = sf.format(help.getModifyTime());
        help.setTime1(format1);
        help.setTime2(format2);
        model.addAttribute("help",help);
        return FebsUtil.view("system/helpcenter/helpcenterDetail");
    }

    /**
     * 跳转新闻修改页(带值)
     * */
    @GetMapping(FebsConstant.VIEW_PREFIX+"system/help/updateHtml/{helpcenterId}")
    public String newsUpdate(@PathVariable Integer helpcenterId,Model model){
        Helpcenter help = helpcenterService.helpDetail(helpcenterId);
        model.addAttribute("help",help);
        return FebsUtil.view("system/helpcenter/helpcenterUpdate");
    }
}
