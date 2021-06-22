package cc.mrbird.febs.shop.service;

import cc.mrbird.febs.shop.entity.Specifications;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author weiZiHao
 * @date 2021-03-02 10:56:43
 */
public interface ISpecificationsService extends IService<Specifications> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param specifications specifications
     * @return IPage<Specifications>
     */
    IPage<Specifications> findSpecificationss(QueryRequest request, Specifications specifications);

    /**
     * 查询（所有）
     *
     * @param specifications specifications
     * @return List<Specifications>
     */
    List<Specifications> findSpecificationss(Specifications specifications);

    /**
     * 新增
     *
     * @param specifications specifications
     */
    void createSpecifications(Specifications specifications);

    /**
     * 修改
     *
     * @param specifications specifications
     */
    void updateSpecifications(Specifications specifications);

    /**
     * 删除
     *
     * @param specifications specifications
     */
    void deleteSpecifications(Specifications specifications);
}
