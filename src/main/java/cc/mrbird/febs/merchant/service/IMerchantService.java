package cc.mrbird.febs.merchant.service;

import cc.mrbird.febs.merchant.entity.Merchant;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author A stubborn man
 * @date 2021-03-08 10:35:20
 */
public interface IMerchantService extends IService<Merchant> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param merchant merchant
     * @return IPage<Merchant>
     */
    IPage<Merchant> findMerchants(QueryRequest request, Merchant merchant);

    /**
     * 查询（所有）
     *
     * @param merchant merchant
     * @return List<Merchant>
     */
    List<Merchant> findMerchants(Merchant merchant);

    /**
     * 新增
     *
     * @param merchant merchant
     */
    void createMerchant(Merchant merchant);

    /**
     * 修改
     *
     * @param merchant merchant
     */
    void updateMerchant(Merchant merchant);

    /**
     * 删除
     *
     * @param merchant merchant
     */
    void deleteMerchant(Merchant merchant);
}
