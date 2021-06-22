package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.DateUtil;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.companyprofile.entity.CompanyProfile;
import cc.mrbird.febs.companyprofile.service.impl.CompanyProfileServiceImpl;
import cc.mrbird.febs.outsourcing.entity.Outsourcing;
import cc.mrbird.febs.outsourcing.service.impl.OutsourcingServiceImpl;
import cc.mrbird.febs.system.entity.User;
import cc.mrbird.febs.system.service.IUserDataPermissionService;
import cc.mrbird.febs.system.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author MrBird
 */
@Controller("systemView")
@RequiredArgsConstructor
public class ViewController extends BaseController {

    private final IUserService userService;
    private final IUserDataPermissionService userDataPermissionService;
    private final OutsourcingServiceImpl outsourcingService;
    private final CompanyProfileServiceImpl companyProfileService;

    @GetMapping("login")
    @ResponseBody
    public Object login(HttpServletRequest request) {
        if (FebsUtil.isAjaxRequest(request)) {
            throw new ExpiredSessionException();
        } else {
            ModelAndView mav = new ModelAndView();
            mav.setViewName(FebsUtil.view("login"));
            return mav;
        }
    }

    @GetMapping("unauthorized")
    public String unauthorized() {
        return FebsUtil.view("error/403");
    }


    @GetMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @GetMapping("index")
    public String index(Model model) {
        User principal = userService.findByName(getCurrentUser().getUsername());
        userService.doGetUserAuthorizationInfo(principal);
        principal.setPassword("It's a secret");
        model.addAttribute("user", principal);
        model.addAttribute("permissions", principal.getStringPermissions());
        model.addAttribute("roles", principal.getRoles());
        return "index";
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "layout")
    public String layout() {
        return FebsUtil.view("layout");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "password/update")
    public String passwordUpdate() {
        return FebsUtil.view("system/user/passwordUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "user/profile")
    public String userProfile() {
        return FebsUtil.view("system/user/userProfile");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "user/avatar")
    public String userAvatar() {
        return FebsUtil.view("system/user/avatar");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "user/profile/update")
    public String profileUpdate() {
        return FebsUtil.view("system/user/profileUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user")
    @RequiresPermissions("user:view")
    public String systemUser() {
        return FebsUtil.view("system/user/user");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user/add")
    @RequiresPermissions("user:add")
    public String systemUserAdd() {
        return FebsUtil.view("system/user/userAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user/detail/{username}")
    @RequiresPermissions("user:view")
    public String systemUserDetail(@PathVariable String username, Model model) {
        resolveUserModel(username, model, true);
        return FebsUtil.view("system/user/userDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user/update/{username}")
    @RequiresPermissions("user:update")
    public String systemUserUpdate(@PathVariable String username, Model model) {
        resolveUserModel(username, model, false);
        return FebsUtil.view("system/user/userUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/role")
    @RequiresPermissions("role:view")
    public String systemRole() {
        return FebsUtil.view("system/role/role");
    }

    /***
     *商城管理跳转
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/shop")
    @RequiresPermissions("role:view")
    public String systemShop() {
        return FebsUtil.view("system/shop/shop");
    }

    /**
     * 外包业务管理
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/material")
    public String systemMaterial() {
        return FebsUtil.view("system/material/material");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/stocks")
    @RequiresPermissions("role:view")
    public String systemStocks() {return FebsUtil.view("system/stocks/stocks");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/thesaurus")
    @RequiresPermissions("role:view")
    public String systemThesaurus() {return FebsUtil.view("system/thesaurus/thesaurus");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/frp")
    @RequiresPermissions("role:view")
    public String systemFrp() {return FebsUtil.view("system/frp/frp");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/frpbding")
    @RequiresPermissions("role:view")
    public String systemFrpbding() {return FebsUtil.view("system/frp/frpbding");
    }


    /**
     * 跳转材料添加页面
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/material/add")
    public String systemMaterialAdd() {
        return FebsUtil.view("system/material/materialAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/outsourcing")
    public String systemOutsourcing(Model model) {
        QueryWrapper<Outsourcing> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("OUTSOURCING_ID", "OUTSOURCING_NEWS");
        List<Outsourcing> outsourcings = outsourcingService.getBaseMapper().selectList(queryWrapper);
        Outsourcing outsourcing = outsourcings.get(0);
        model.addAttribute("out",outsourcing);
        return FebsUtil.view("system/outsourcing/outsourcing");
    }

    /**
     * 用户端管理跳转
     * */
    @GetMapping(FebsConstant.VIEW_PREFIX+"/user/Administration")
    public String userAdministration(){
        return FebsUtil.view("system/userAdministration/userAdministration");
    }

    /**
     * 公司介绍的页面跳转（带参）
     * */
    @GetMapping(FebsConstant.VIEW_PREFIX+"/system/company")
    public String companyNews(Model model){
        QueryWrapper<CompanyProfile> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("COMPANY_PROFILE_ID", "COMPANY_PROFILE");
        List<CompanyProfile> companyProfiles = companyProfileService.getBaseMapper().selectList(queryWrapper);
        CompanyProfile companyProfile = companyProfiles.get(0);
        model.addAttribute("out",companyProfile);
        return FebsUtil.view("system/company/company");
    }

    /**
     * 新闻页面跳转
     * */
    @GetMapping(FebsConstant.VIEW_PREFIX+"/system/news")
    public String news(){
        return FebsUtil.view("system/news/news");
    }

    /**
     * 跳转帮助中心页
     * */
    @GetMapping(FebsConstant.VIEW_PREFIX+"system/helpcenter")
    public String helpCenter(){
        return FebsUtil.view("system/helpcenter/helpcenter");
    }
    /**
     * 交货地址管理页面
     * */
    @GetMapping(FebsConstant.VIEW_PREFIX+"system/delivery")
    public String delivery(){
        return FebsUtil.view("system/deliveryTime/deliveryTime");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/menu")
    @RequiresPermissions("menu:view")
    public String systemMenu() {
        return FebsUtil.view("system/menu/menu");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/dept")
    @RequiresPermissions("dept:view")
    public String systemDept() {
        return FebsUtil.view("system/dept/dept");
    }

    @RequestMapping(FebsConstant.VIEW_PREFIX + "index")
    public String pageIndex() {
        return FebsUtil.view("index");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "404")
    public String error404() {
        return FebsUtil.view("error/404");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "403")
    public String error403() {
        return FebsUtil.view("error/403");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "500")
    public String error500() {
        return FebsUtil.view("error/500");
    }

    private void resolveUserModel(String username, Model model, Boolean transform) {
        User user = userService.findByName(username);
        String deptIds = userDataPermissionService.findByUserId(String.valueOf(user.getUserId()));
        user.setDeptIds(deptIds);
        model.addAttribute("user", user);
        if (transform) {
            String sex = user.getSex();
            switch (sex) {
                case User.SEX_MALE:
                    user.setSex("男");
                    break;
                case User.SEX_FEMALE:
                    user.setSex("女");
                    break;
                default:
                    user.setSex("保密");
                    break;
            }
        }
        if (user.getLastLoginTime() != null) {
            model.addAttribute("lastLoginTime", DateUtil.getDateFormat(user.getLastLoginTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        }
    }
}
