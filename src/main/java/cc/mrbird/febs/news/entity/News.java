package cc.mrbird.febs.news.entity;

import java.io.Serializable;
import java.util.Date;

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
 * @author A stubborn man
 * @date 2021-03-03 17:25:20
 */
@Data
@TableName("t_news")
@Excel("新闻信息表")
public class News implements Serializable {

    /**
     * 新闻id
     */
    @TableId(value = "NEWS_ID", type = IdType.AUTO)
    private Integer newsId;

    /**
     * 新闻标题
     */
    @ExcelField("新闻标题")
    @TableField("NEWS_TITLE")
    private String newsTitle;

    /**
     * 新闻内容
     */
    @ExcelField("新闻内容")
    @TableField("NEWS_CONTENT")
    private String newsContent;

    /**
     * 
     */
    @ExcelField(value = "创建时间",writeConverter = TimeConverter.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("MODIFY_TIME")
    private Date modifyTime;

    @TableField(exist = false)
    private String time1;
    @TableField(exist = false)
    private String time2;
}
