package cc.mrbird.febs.thesaurus.mapper;

import cc.mrbird.febs.stocks.entity.Stocks;
import cc.mrbird.febs.thesaurus.entity.Thesaurus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 *  Mapper
 *
 * @author bjw
 * @date 2021-03-04 10:12:23
 */
public interface ThesaurusMapper extends BaseMapper<Thesaurus> {

    long countthesaurusDetail(@Param("thesaurus") Thesaurus thesaurus);

    <T> IPage<Thesaurus> findtthesaurusDetailPage(Page<T> page, @Param("thesaurus") Thesaurus thesaurus);

}
