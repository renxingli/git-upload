package cc.mrbird.febs.delivery.entity;

import java.util.Date;

import cc.mrbird.febs.common.converter.TimeConverter;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import jdk.Exported;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *  Entity
 *
 * @author A stubborn man
 * @date 2021-03-05 11:38:07
 */
@Data
@TableName("t_delivery_time")
@Excel("交货时间表")
public class DeliveryTime {

    /**
     * 交货时间Id
     */
    @TableId(value = "DELIVERY_TIME_ID", type = IdType.AUTO)
    private Long deliveryTimeId;

    /**
     * 备注
     */
    @ExcelField("备注信息")
    @TableField("REMARK")
    private String remark;

    /**
     * 打折率
     */
    @ExcelField(value = "打折率",width = 100)
    @TableField("DISCOUNT")
    private Double discount;

    /**
     * 创建时间
     */
    @ExcelField(value = "创建时间",writeConverter = TimeConverter.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("MODIFY_TIME")
    private Date modifyTime;

    /**
     * 转换格式后的创建时间(传递前台时使用)
     * */
    @TableField(exist = false)
    private String time1;

    /**
     * 转换格式后的最后修改时间(传递前台时使用)
     * */
    @TableField(exist = false)
    private String time2;
}
