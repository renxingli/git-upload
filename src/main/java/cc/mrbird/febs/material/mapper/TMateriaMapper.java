package cc.mrbird.febs.material.mapper;

import cc.mrbird.febs.material.entity.TMateria;
import cc.mrbird.febs.shop.entity.Shop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 *  Mapper
 *
 * @author bjw
 * @date 2021-03-01 11:31:45
 */
public interface TMateriaMapper extends BaseMapper<TMateria> {

    long counttMateriaDetail(@Param("tMateria") TMateria tMateria);

    <T> IPage<TMateria> findtMateriaDetailPage(Page<T> page, @Param("tMateria") TMateria tMateria);

}
