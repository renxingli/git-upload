package cc.mrbird.febs.shop.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.shop.entity.Specifications;
import cc.mrbird.febs.shop.entity.Type;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author A stubborn man
 * @create 2021/2/25 8:52
 * @Description 该类描述
 */
public interface TypeService extends IService<Type> {
    IPage<Type> findType(QueryRequest request, Type type);
    /**
     * 删除
     *
     * @param type type
     */
    void deleteType(Type type);
}
