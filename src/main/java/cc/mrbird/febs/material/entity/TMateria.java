package cc.mrbird.febs.material.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *  Entity
 *
 * @author bjw
 * @date 2021-03-01 11:31:45
 */
@Data
@TableName("t_materia")
public class TMateria {

    /**
     * 
     */
    @TableId(value = "MATERIAL_ID", type = IdType.AUTO)
    private Integer materialId;

    /**
     * 
     */
    @TableField("MATERIAL_NAME")
    private String materialName;

    /**
     * 
     */
    @TableField("CONTACT")
    private String contact;

    /**
     * 
     */
    @TableField("PHONE")
    private String phone;

    /**
     * 
     */
    @TableField("AREA")
    private String area;

    /**
     * 
     */
    @TableField("ADDRESS")
    private String address;

    /**
     * 
     */
    @TableField("SALES_AREA")
    private String salesArea;

    /**
     * 
     */
    @TableField("BINDING_GOODS")
    private String bindingGoods;

    /**
     * 
     */
    @TableField("STATUS")
    private String status;

    /**
     * 
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 
     */
    @TableField("MODIFY_TIME")
    private Date modifyTime;

}
