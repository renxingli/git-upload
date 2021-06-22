package cc.mrbird.febs.shop.entity;

import java.util.Date;
import java.math.BigDecimal;

import cc.mrbird.febs.common.converter.TimeConverter;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *  Entity
 *
 * @author rxl
 * @date 2021-02-23 14:54:52
 */
@Data
@TableName("t_shop")
@Excel("商品信息表")
public class Shop {

    /**
     * 商品id
     */
    @TableId(value = "COMMODITY_ID", type = IdType.AUTO)
    private Integer commodityId;

    /**
     * 产品序号
     */
    @ExcelField("产品序号")
    @TableField("COMMODITY_NUMBER")
    private String commodityNumber;

    /**
     * 商品名称
     */
    @ExcelField("产品名称")
    @TableField("COMMODITY_NAME")
    private String commodityName;

    /**
     * 商品图片
     */
    @TableField("COMMODITY_IMAGE")
    private String commodityImage;


    /**
     * 商品生产价格
     */
    @ExcelField("商品生产价格")
    @TableField("COMMODITY_COST_PRICE")
    private BigDecimal commodityCostPrice;
    /**
     * 商品出售价格
     */
    @ExcelField("商品出售价格")
    @TableField("COMMODITY_SELLING_PRICE")
    private BigDecimal commoditySellingPrice;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("CREATE_TIME")
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;

    /**
     * 商品分类id
     * */
    @TableField("TYPE_ID")
    private Integer typeId;
    /**
     * 最后修改时间
     */
    @ExcelField(value = "最后修改时间", writeConverter = TimeConverter.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("MODIFY_TIME")
    private Date modifyTime;

    /**
     * 商品类别名称
     * */
    @ExcelField("商品类别")
    @TableField(exist = false)
    private String shopType;

    /***
     * 规格名称
     */
    @ExcelField("商品规格")
    @TableField(exist = false)
    private String specificationsName;

    /***
     * 规格id
     */
    @TableField(exist = false)
    private Integer specificationsId;

    @TableField(exist = false)
    private String specificationsIds;

    /**
     * 商品规格(cm)
     */
    @TableField(exist = false)
    private String commoditySpecifications;

    @TableField(exist = false)
    private String createTimeFrom;
    @TableField(exist = false)
    private String createTimeTo;

}
