package cc.mrbird.febs.outsourcing.service;

import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.outsourcing.entity.Outsourcing;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *  Service接口
 *
 * @author rxl
 * @date 2021-02-26 10:37:09
 */
public interface IOutsourcingService extends IService<Outsourcing> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param outsourcing outsourcing
     * @return IPage<Outsourcing>
     */
    IPage<Outsourcing> findOutsourcings(QueryRequest request, Outsourcing outsourcing);

    /**
     * 查询（所有）
     *
     * @param outsourcing outsourcing
     * @return List<Outsourcing>
     */
    List<Outsourcing> findOutsourcings(Outsourcing outsourcing);

    /**
     * 新增
     *
     * @param outsourcing outsourcing
     */
    void createOutsourcing(Outsourcing outsourcing);

    /**
     * 修改
     *
     * @param outsourcing outsourcing
     */
    void updateOutsourcing(Outsourcing outsourcing);

    /**
     * 删除
     *
     * @param outsourcing outsourcing
     */
    void deleteOutsourcing(String[] outsourcings);

    /**
     * 添加
     * */
    FebsResponse AddOutsourcing(HttpServletRequest request);
}
