package cc.mrbird.febs.companyprofile.service;

import cc.mrbird.febs.companyprofile.entity.CompanyProfile;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author A stubborn man
 * @date 2021-03-03 16:55:22
 */
public interface ICompanyProfileService extends IService<CompanyProfile> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param companyProfile companyProfile
     * @return IPage<CompanyProfile>
     */
    IPage<CompanyProfile> findCompanyProfiles(QueryRequest request, CompanyProfile companyProfile);

    /**
     * 查询（所有）
     *
     * @param companyProfile companyProfile
     * @return List<CompanyProfile>
     */
    List<CompanyProfile> findCompanyProfiles(CompanyProfile companyProfile);

    /**
     * 新增
     *
     * @param companyProfile companyProfile
     */
    void createCompanyProfile(CompanyProfile companyProfile);

    /**
     * 修改
     *
     * @param companyProfile companyProfile
     */
    void updateCompanyProfile(CompanyProfile companyProfile);

    /**
     * 删除
     *
     * @param companyProfile companyProfile
     */
    void deleteCompanyProfile(CompanyProfile companyProfile);
}
