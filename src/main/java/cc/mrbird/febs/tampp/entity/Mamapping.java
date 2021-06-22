package cc.mrbird.febs.tampp.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *  Entity
 *
 * @author bjw
 * @date 2021-03-06 14:02:49
 */
@Data
@TableName("t_mamapping")
public class Mamapping {

    /**
     * 
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField("FRP_ID")
    private Integer frpId;

    /**
     * 
     */
    @TableField("BANGDING_ID")
    private Integer bangdingId;

}
