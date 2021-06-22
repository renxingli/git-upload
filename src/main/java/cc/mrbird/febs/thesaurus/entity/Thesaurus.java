package cc.mrbird.febs.thesaurus.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *  Entity
 *
 * @author bjw
 * @date 2021-03-04 10:12:23
 */
@Data
@TableName("t_thesaurus")
public class Thesaurus {

    /**
     * 
     */
    @TableId(value = "THESAURUS_ID", type = IdType.AUTO)
    private Long thesaurusId;

    /**
     * 名称
     */
    @TableField("THESAURUS_NAME")
    private String thesaurusName;

    /**
     * 同义词
     */
    @TableField("THESAURUS_NAMES")
    private String thesaurusNames;

}
