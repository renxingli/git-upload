package cc.mrbird.febs.companyprofile.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *  Entity
 *
 * @author A stubborn man
 * @date 2021-03-03 16:55:22
 */
@Data
@TableName("t_company_profile")
public class CompanyProfile {

    /**
     * 公司介绍id
     */
    @TableId(value = "COMPANY_PROFILE_ID", type = IdType.AUTO)
    private Integer companyProfileId;

    /**
     * 公司介绍
     */
    @TableField("COMPANY_PROFILE")
    private String companyProfile;

}
