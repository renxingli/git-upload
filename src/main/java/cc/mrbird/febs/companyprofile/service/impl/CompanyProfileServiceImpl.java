package cc.mrbird.febs.companyprofile.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.companyprofile.entity.CompanyProfile;
import cc.mrbird.febs.companyprofile.mapper.CompanyProfileMapper;
import cc.mrbird.febs.companyprofile.service.ICompanyProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 *  Service实现
 *
 * @author A stubborn man
 * @date 2021-03-03 16:55:22
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CompanyProfileServiceImpl extends ServiceImpl<CompanyProfileMapper, CompanyProfile> implements ICompanyProfileService {

    private final CompanyProfileMapper companyProfileMapper;

    @Override
    public IPage<CompanyProfile> findCompanyProfiles(QueryRequest request, CompanyProfile companyProfile) {
        LambdaQueryWrapper<CompanyProfile> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<CompanyProfile> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<CompanyProfile> findCompanyProfiles(CompanyProfile companyProfile) {
	    LambdaQueryWrapper<CompanyProfile> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createCompanyProfile(CompanyProfile companyProfile) {
        this.save(companyProfile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCompanyProfile(CompanyProfile companyProfile) {
        this.saveOrUpdate(companyProfile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCompanyProfile(CompanyProfile companyProfile) {
        LambdaQueryWrapper<CompanyProfile> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
