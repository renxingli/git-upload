package cc.mrbird.febs.frp.entity;

import java.math.BigDecimal;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *  Entity
 *
 * @author bjw
 * @date 2021-03-05 13:52:21
 */
@Data
@TableName("t_frp")
public class Frp {

    /**
     * 
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 名字
     */
    @TableField("NAME")
    private String name;

    /**
     * 类型
     */
    @TableField("TYPE")
    private String type;

    /**
     * 价格
     */
    @TableField("PRICE")
    private BigDecimal price;
}
