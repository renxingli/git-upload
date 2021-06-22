package cc.mrbird.febs.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author A stubborn man
 * @create 2021/2/24 14:11
 * @Description 该类描述
 */

@Data
@TableName("t_type")
public class Type {


    public static final String TYPE_NAME="还未分类";
    /**
     *商品类别id
     */
    @TableField(value = "TYPE_ID")
    private Integer typeId;

    /**
     *商品类别名称
     */
    @TableField("TYPE_NAME")
    private String typeName;
}
