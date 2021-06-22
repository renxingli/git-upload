package cc.mrbird.febs.shop.service.Impl;

import cc.mrbird.febs.common.entity.DeptTree;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.TreeUtil;
import cc.mrbird.febs.shop.entity.Specifications;
import cc.mrbird.febs.shop.entity.Type;
import cc.mrbird.febs.shop.mapper.SpecificationsMapper;
import cc.mrbird.febs.shop.mapper.TypeMapper;
import cc.mrbird.febs.shop.service.TypeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author A stubborn man
 * @create 2021/2/24 14:14
 * @Description 该类描述
 */
@Service
@RequiredArgsConstructor
public class ShopTypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

    private final TypeMapper typeMapper;
    private final SpecificationsMapper specificationsMapper;

    @Override
    public IPage<Type> findType(QueryRequest request, Type type) {
        LambdaQueryWrapper<Type> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        queryWrapper.setEntity(type);
        Page<Type> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }
    private List<DeptTree<Type>> convertDept(List<Type> Type) {
        List<DeptTree<Type>> trees = new ArrayList<>();
        Type.forEach(dept -> {
            DeptTree<cc.mrbird.febs.shop.entity.Type> tree = new DeptTree<>();
            tree.setId(String.valueOf(dept.getTypeId()));
            tree.setName(String.valueOf(dept.getTypeName()));
            trees.add(tree);
        });
        return trees;
    }

    private List<DeptTree<Specifications>> convertDept1(List<Specifications> Type) {
        List<DeptTree<Specifications>> trees = new ArrayList<>();
        Type.forEach(dept -> {
            DeptTree<Specifications> tree = new DeptTree<>();
            tree.setId(String.valueOf(dept.getSpecificationsId()));
            tree.setName(String.valueOf(dept.getSpecificationsName()));
            trees.add(tree);
        });
        return trees;
    }
    public List<DeptTree<Type>> findShopType() {
        List<Type> typeList = typeMapper.selectList(new QueryWrapper<>());
        List<DeptTree<Type>> trees = convertDept(typeList);
        return TreeUtil.buildDeptTree(trees);
    }
    public List<DeptTree<Specifications>> findShopGuiGe() {
        List<Specifications> typeList = specificationsMapper.selectList(new QueryWrapper<>());
        List<DeptTree<Specifications>> trees = convertDept1(typeList);
        return TreeUtil.buildDeptTree(trees);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteType(Type type) {
        LambdaQueryWrapper<Type> wrapper = new LambdaQueryWrapper<>();
        // TODO 设置删除条件
        wrapper.setEntity(type);
        this.remove(wrapper);
    }
}
