package cc.mrbird.febs.helpcenter.service;

import cc.mrbird.febs.helpcenter.entity.Helpcenter;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author A stubborn man
 * @date 2021-03-05 09:33:25
 */
public interface IHelpcenterService extends IService<Helpcenter> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param helpcenter helpcenter
     * @return IPage<Helpcenter>
     */
    IPage<Helpcenter> findHelpcenters(QueryRequest request, Helpcenter helpcenter);

    /**
     * 查询（所有）
     *
     * @param helpcenter helpcenter
     * @return List<Helpcenter>
     */
    List<Helpcenter> findHelpcenters(Helpcenter helpcenter);

    /**
     * 新增
     *
     * @param helpcenter helpcenter
     */
    void createHelpcenter(Helpcenter helpcenter);

    /**
     * 修改
     *
     * @param helpcenter helpcenter
     */
    void updateHelpcenter(Helpcenter helpcenter);

    /**
     * 删除
     *
     * @param helpcenter helpcenter
     */
    void deleteHelpcenter(Helpcenter helpcenter);

    void deleteHelps(String[] helpcenterIds);
}
