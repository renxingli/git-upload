package cc.mrbird.febs.shop.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *  Entity
 *
 * @author weiZiHao
 * @date 2021-03-02 10:56:43
 */
@Data
@TableName("t_specifications")
public class Specifications {

    /**
     * 
     */
    @TableId(value = "SPECIFICATIONS_ID", type = IdType.AUTO)
    private Integer specificationsId;

    /**
     * 
     */
    @TableField("SPECIFICATIONS_NAME")
    private String specificationsName;

    /**
     * 
     */
    @TableField("COMMODITY_ID")
    private Integer commodityId;

}
