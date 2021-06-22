package cc.mrbird.febs.shop.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.Strings;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.shop.entity.Shop;
import cc.mrbird.febs.shop.entity.Type;
import cc.mrbird.febs.shop.mapper.ShopMapper;
import cc.mrbird.febs.shop.service.Impl.ShopTypeServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author A stubborn man
 * @create 2021/2/24 14:29
 * @Description 该类描述
 */

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("shopType")
public class ShopTypeController extends BaseController {

    private final ShopTypeServiceImpl shopTypeService;
    private final ShopMapper shopMapper;

    @GetMapping("select/tree")
    @ControllerEndpoint(exceptionMessage = "获取分类树失败")
    public FebsResponse getDeptTree() throws FebsException {
        return new FebsResponse().success().data(shopTypeService.findShopType());
    }

    @PostMapping("gg/add")
    @ResponseBody
    public FebsResponse addGG(HttpServletRequest request) {
        String typename = request.getParameter("typeName");
        HttpSession session = request.getSession();
        Object typeName = session.getAttribute("typeName");
        if (typeName != null) {
            String name = (String) typeName;
            String s = name + typename + ",";
            session.setAttribute("typeName", s);
        } else {
            session.setAttribute("typeName", typename + ",");
        }
        return new FebsResponse().success();
    }

    //删除session
    @GetMapping("del/session")
    public static void delSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("typeName");
    }

    @PostMapping("type/add")
    public FebsResponse addType(Type type) {
        this.shopTypeService.getBaseMapper().insert(type);
        return new FebsResponse().success();
    }

    @GetMapping("type/del")
    @ControllerEndpoint(exceptionMessage = "删除类别失败")
    public FebsResponse delType(Type type) {
        System.out.println(type);
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("TYPE_ID").eq("TYPE_ID", type.getTypeId());
        Integer integer = shopMapper.selectCount(queryWrapper);
        System.out.println(integer);
        if (integer != 0) {
            throw new FebsException("该类别下有绑定数据");
        } else {
            this.shopTypeService.deleteType(type);
        }
        return new FebsResponse().success();
    }


    @GetMapping("select/guiGe/tree")
    @ControllerEndpoint(exceptionMessage = "获取分类树失败")
    public FebsResponse getShopTree() throws FebsException {
        return new FebsResponse().success().data(shopTypeService.findShopGuiGe());
    }
}
