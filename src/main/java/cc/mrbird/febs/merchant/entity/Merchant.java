package cc.mrbird.febs.merchant.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *  Entity
 *
 * @author A stubborn man
 * @date 2021-03-08 10:35:20
 */
@Data
@TableName("t_merchant")
public class Merchant {

    /**
     * 商家ID
     */
    @TableId(value = "MERCHANT_ID", type = IdType.AUTO)
    private Long merchantId;

    /**
     * 姓名
     */
    @TableField("USER_NAME")
    private String userName;

    /**
     * 手机号
     */
    @TableField("PHONE")
    private String phone;

    /**
     * 盐
     */
    @TableField("SALT")
    private String salt;

    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 公司ID
     */
    @TableField("COMPANY_ID")
    private Long companyId;

    /**
     * 公司名称
     */
    @TableField("COMPANY_NAME")
    private String companyName;

    /**
     * 地址
     */
    @TableField("SITE")
    private String site;

    /**
     * 地区
     */
    @TableField("REGION")
    private String region;

    /**
     * 营业执照 图片名称
     */
    @TableField("LICENSE")
    private String license;

    /**
     * 根目录ID
     */
    @TableField("FILE_ID")
    private Long fileId;

    /**
     * 所属oss文件名
     */
    @TableField("FILE_NAME")
    private String fileName;

    /**
     * 审核状态 0 未审核 1审核通过 2未通过
     */
    @TableField("STATE_ID")
    private Integer stateId;

    /**
     * 钱包Id
     */
    @TableField("WALLET_ID")
    private Long walletId;

    /**
     * 云盘存储量Id
     */
    @TableField("MEMORY_ID")
    private Long memoryId;

    /**
     * 余额
     */
    @TableField("BALANCE")
    private Double balance;

    /**
     * 折扣
     */
    @TableField("DISCOUNTS")
    private Double discounts;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("MODIFY_TIME")
    private Date modifyTime;

}
