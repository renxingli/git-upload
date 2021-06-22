package cc.mrbird.febs.stocks.entity;

import java.util.Date;
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
 * @date 2021-03-02 16:09:27
 */
@Data
@TableName("t_stocks")
public class Stocks {

    /**
     * 
     */
    @TableId(value = "RAWMATERIAL_ID", type = IdType.AUTO)
    private Long rawmaterialId;

    /**
     * 名称
     */
    @TableField("RAWMATERIAL_NAME")
    private String rawmaterialName;

    /**
     * 推荐长度
     */
    @TableField("RECOMMEND_LENGTH")
    private BigDecimal recommendLength;
    /**
     * 最小长度
     */
    @TableField("MIN_LENGTH")
    private BigDecimal minLength;
    /**
     * 最大长度
     */
    @TableField("MAX_LENGTH")
    private BigDecimal maxLength;
    /**
     * 间隔长度
     */
    @TableField("INTERVAL_LENGTH")
    private Double intervalLength;

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
