package cc.mrbird.febs.material.service;

import cc.mrbird.febs.material.entity.TMateria;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author bjw
 * @date 2021-03-01 11:31:45
 */
public interface ITMateriaService extends IService<TMateria> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param tMateria tMateria
     * @return IPage<TMateria>
     */
    IPage<TMateria> findTMaterias(QueryRequest request, TMateria tMateria);

    /**
     * 查询（所有）
     *
     * @param tMateria tMateria
     * @return List<TMateria>
     */
    List<TMateria> findTMaterias(TMateria tMateria);

    /**
     * 新增
     *
     * @param tMateria tMateria
     */
    void createTMateria(TMateria tMateria);

    /**
     * 修改
     *
     * @param tMateria tMateria
     */
    void updateTMateria(TMateria tMateria);

    /**
     * 删除
     *
     * @param tMateria tMateria
     */
    void deleteTMateria(TMateria tMateria);
}
