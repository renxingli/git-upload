package cc.mrbird.febs.thesaurus.service;

import cc.mrbird.febs.thesaurus.entity.Thesaurus;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author bjw
 * @date 2021-03-04 10:12:23
 */
public interface IThesaurusService extends IService<Thesaurus> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param thesaurus thesaurus
     * @return IPage<Thesaurus>
     */
    IPage<Thesaurus> findThesauruss(QueryRequest request, Thesaurus thesaurus);

    /**
     * 查询（所有）
     *
     * @param thesaurus thesaurus
     * @return List<Thesaurus>
     */
    List<Thesaurus> findThesauruss(Thesaurus thesaurus);

    /**
     * 新增
     *
     * @param thesaurus thesaurus
     */
    void createThesaurus(Thesaurus thesaurus);

    /**
     * 修改
     *
     * @param thesaurus thesaurus
     */
    void updateThesaurus(Thesaurus thesaurus);

    /**
     * 删除
     *
     * @param thesaurus thesaurus
     */
    void deleteThesaurus(Thesaurus thesaurus);

    void deletethesaurus(String[] thesaurusIds  );

}
