package cc.mrbird.febs.outsourcing.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.Strings;
import cc.mrbird.febs.outsourcing.entity.Outsourcing;
import cc.mrbird.febs.outsourcing.service.impl.OutsourcingServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author A stubborn man
 * @create 2021/3/1 14:07
 * @Description 业务外包板块管理
 */

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("outsourcing")
public class OutsourcingController extends BaseController {

    private final OutsourcingServiceImpl outsourcingService;



    /**
     * 业务外包板块的添加
     * */
    @PostMapping("AddOutsourcing")
    public FebsResponse AddOutsourcing(HttpServletRequest request) {
        return outsourcingService.AddOutsourcing(request);
    }

    @PostMapping("UpdateOutsourcing")
    public FebsResponse AddOutsourcing(@Valid Outsourcing outsourcing) {
        outsourcingService.updateOutsourcing(outsourcing);
        return new FebsResponse().success();
    }

    @GetMapping("AllOutsourcing")
    @ResponseBody
    public FebsResponse AllOutsourcing(QueryRequest request, Outsourcing outsourcing){
        Map<String, Object> dataTable = getDataTable(this.outsourcingService.findOutsourcings(request,outsourcing));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "删除Shop", exceptionMessage = "删除Shop失败")
    @GetMapping("outsourcing/delete/{outsourcings}")
    @ResponseBody
    public FebsResponse deleteOutsourcing(@PathVariable String outsourcings){
        this.outsourcingService.deleteOutsourcing(StringUtils.split(outsourcings,Strings.COMMA));
        return new FebsResponse().success();
    }

}
