package cc.mrbird.febs.tampp.service;


import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.tampp.entity.Mamapping;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author bjw
 * @date 2021-03-06 14:02:49
 */
public interface IMamappingService extends IService<Mamapping> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param mamapping mamapping
     * @return IPage<Mamapping>
     */
    IPage<Mamapping> findMamappings(QueryRequest request, Mamapping mamapping);

    /**
     * 查询（所有）
     *
     * @param mamapping mamapping
     * @return List<Mamapping>
     */
    List<Mamapping> findMamappings(Mamapping mamapping);

    /**
     * 新增
     *
     * @param mamapping mamapping
     */
    void createMamapping(Mamapping mamapping);

    /**
     * 修改
     *
     * @param mamapping mamapping
     */
    void updateMamapping(Mamapping mamapping);

    /**
     * 删除
     *
     * @param mamapping mamapping
     */
    //void deleteMamapping(Mamapping mamapping);
    void deleteMamapping(String[] IDS);
}
