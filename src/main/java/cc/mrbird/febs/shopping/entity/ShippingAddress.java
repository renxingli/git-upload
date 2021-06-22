package cc.mrbird.febs.shopping.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *  Entity
 *
 * @author weiZiHao
 * @date 2021-03-02 14:05:22
 */
@Data
@TableName("t_shipping_address")
public class ShippingAddress {

    /**
     * 配送地址ID
     */
    @TableId(value = "SHIPPING_ADDRESS_ID", type = IdType.AUTO)
    private Long shippingAddressId;

    /**
     * 企业ID
     */
    @TableField("COMPANY_ID")
    private Long companyId;

    /**
     * 手机号
     */
    @TableField("PHONE")
    private String phone;

    /**
     * 收件人姓名
     */
    @TableField("REAL_NAME")
    private String realName;

    /**
     * 审核状态 0 审核中 1通过 2拒绝
     */
    @TableField("STATE_ID")
    private Integer stateId;

    /**
     * 是否默认 1是 0否
     */
    @TableField("IS_DEFAULT")
    private Integer isDefault;

    /**
     * 地区
     */
    @TableField("REGION")
    private String region;

    /**
     * 详细地址
     */
    @TableField("SITE")
    private String site;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * x修改时间
     */
    @TableField("MODIFY_TIME")
    private Date modifyTime;

}
