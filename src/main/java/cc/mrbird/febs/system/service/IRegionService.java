package cc.mrbird.febs.system.service;

import cc.mrbird.febs.system.entity.Region;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author weiZiHao
 * @date 2021-03-08 10:04:00
 */
public interface IRegionService extends IService<Region> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param region region
     * @return IPage<Region>
     */
    IPage<Region> findRegions(QueryRequest request, Region region);

    /**
     * 查询（所有）
     *
     * @param region region
     * @return List<Region>
     */
    List<Region> findRegions(Region region);

    /**
     * 新增
     *
     * @param region region
     */
    void createRegion(Region region);

    /**
     * 修改
     *
     * @param region region
     */
    void updateRegion(Region region);

    /**
     * 删除
     *
     * @param region region
     */
    void deleteRegion(Region region);
}
