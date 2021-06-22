package cc.mrbird.febs.system.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Entity
 *
 * @author weiZiHao
 * @date 2021-03-08 10:04:00
 */
@Data
@TableName("t_region")
public class Region {

    /**
     * 区域主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 区域名称
     */
    @TableField("name")
    private String name;

    /**
     * 区域上级标识
     */
    @TableField("pid")
    private Integer pid;

    /**
     * 地名简称
     */
    @TableField("sname")
    private String sname;

    /**
     * 区域等级
     */
    @TableField("level")
    private Integer level;

    /**
     * 区域编码
     */
    @TableField("citycode")
    private String citycode;

    /**
     * 邮政编码
     */
    @TableField("yzcode")
    private String yzcode;

    /**
     * 组合名称
     */
    @TableField("mername")
    private String mername;

    /**
     *
     */
    @TableField("Lng")
    private Float Lng;
    /**
     *
     */
    @TableField("Lat")
    private Float Lat;
    /**
     *
     */
    @TableField("pinyin")
    private String pinyin;

    /**
     * 是否激活 0未激活 1激活 2关闭
     */
    @TableField("STATE_ID")
    private Integer stateId = 1;

}
