package cc.mrbird.febs.frp.service;

import cc.mrbird.febs.frp.entity.Frp;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.stocks.entity.Stocks;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author bjw
 * @date 2021-03-05 13:52:21
 */
public interface IFrpService extends IService<Frp> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param frp frp
     * @return IPage<Frp>
     */
    IPage<Frp> findFrps(QueryRequest request, Frp frp);

    /**
     * 查询（所有）
     *
     * @param frp frp
     * @return List<Frp>
     */
    List<Frp> findFrps(Frp frp);

    List<Frp> qxfindFrps(Frp frp);

    /**
     * 新增
     *
     * @param frp frp
     */
    void createFrp(Frp frp);

    /**
     * 修改
     *
     * @param frp frp
     */
    void updateFrp(Frp frp);

    /**
     * 删除
     *
     * @param frp frp
     */

    void deleteFrp(String[] IDS);

    IPage<Frp> findFrp(QueryRequest request, Frp frp);
}
