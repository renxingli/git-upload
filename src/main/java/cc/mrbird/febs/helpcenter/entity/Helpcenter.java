package cc.mrbird.febs.helpcenter.entity;

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
 * @date 2021-03-05 09:33:25
 */
@Data
@TableName("t_helpcenter")
public class Helpcenter {

    /**
     * id
     */
    @TableId(value = "HELPCENTER_ID", type = IdType.AUTO)
    private Integer helpcenterId;

    /**
     * 标题
     */
    @TableField("HELPCENTER_TITLE")
    private String helpcenterTitle;

    /**
     * 内容
     */
    @TableField("HEPLCENTER_CONTENT")
    private String heplcenterContent;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @TableField("MODIFY_TIME")
    private Date modifyTime;

    /**
    * 转换传递的创建时间
    * */
    @TableField(exist = false)
    private String time1;

    /**
     * 转换传递的最后修改时间
     * */
    @TableField(exist = false)
    private String time2;
}
