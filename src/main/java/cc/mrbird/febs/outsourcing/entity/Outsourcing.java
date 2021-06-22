package cc.mrbird.febs.outsourcing.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *  Entity
 *
 * @author rxl
 * @date 2021-02-26 10:37:09
 */
@Data
@TableName("t_outsourcing")
public class Outsourcing {

    /**
     * 业务外包id
     */
    @TableId(value = "OUTSOURCING_ID", type = IdType.AUTO)
    private Integer outsourcingId;

    /**
     * 业务外包内容
     */
    @TableField("OUTSOURCING_NEWS")
    private String outsourcingNews;

}
