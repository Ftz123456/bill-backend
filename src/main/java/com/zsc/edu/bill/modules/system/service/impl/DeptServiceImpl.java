package com.zsc.edu.bill.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsc.edu.bill.exception.ConstraintException;
import com.zsc.edu.bill.modules.system.dto.DeptDto;
import com.zsc.edu.bill.modules.system.entity.Dept;
import com.zsc.edu.bill.modules.system.mapper.DeptMapper;
import com.zsc.edu.bill.modules.system.repo.DeptRepository;
import com.zsc.edu.bill.modules.system.service.DeptService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author Yao
 * @since 2023-04-06
 */
@AllArgsConstructor
@Service
public class DeptServiceImpl extends ServiceImpl<DeptRepository, Dept> implements DeptService {

    private final DeptMapper mapper;

    @Override
    public Dept create(DeptDto dto) {
        boolean existsByName = count(new LambdaQueryWrapper<Dept>().eq(Dept::getName, dto.getName())) > 0;
        if (existsByName) {
            throw new ConstraintException("name", dto.name, "部门已存在");
        }
        Dept dept = mapper.toEntity(dto);
        save(dept);
        return dept;
    }

    @Override
    public Boolean edit(DeptDto dto, Long id) {
        boolean isExists = count(new LambdaQueryWrapper<Dept>().ne(Dept::getId, id).eq(Dept::getName, dto.getName())) > 0;
        if (isExists) {
            throw new ConstraintException("name", dto.name, "同名部门已存在");
        }
        return update(dto.updateWrapper(id));
    }

    @Override
    public Boolean toggle(Long id) {
        Dept dept = getById(id);
        dept.setEnabled(!dept.getEnabled());
        return updateById(dept);
    }

//    @Override
//    public Dept listTree(Long deptId) {
//        Dept rootDept;
//        List<Dept> all = list(null);
//        HashSet<Dept> deptList = new HashSet<>(all);
//        rootDept = DeptTreeUtil.initTree(deptList);
//        if (deptId != null) {
//            return rootDept.id.equals(deptId) ? rootDept : DeptTreeUtil.getChildNode(rootDept.children, deptId);
//        }
//        return rootDept;
//    }
}
