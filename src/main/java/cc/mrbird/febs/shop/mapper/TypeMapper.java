package cc.mrbird.febs.shop.mapper;

import cc.mrbird.febs.shop.entity.Shop;
import cc.mrbird.febs.shop.entity.Type;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author A stubborn man
 * @create 2021/2/24 21:26
 * @Description 该类描述
 */

@Mapper
public interface TypeMapper extends BaseMapper<Type> {
}
