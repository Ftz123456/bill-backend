package com.zsc.edu.bill.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsc.edu.bill.domain.DeptBuilder;
import com.zsc.edu.bill.exception.ConstraintException;
import com.zsc.edu.bill.modules.system.dto.DeptDto;
import com.zsc.edu.bill.modules.system.entity.Dept;
import com.zsc.edu.bill.modules.system.repo.DeptRepository;
import com.zsc.edu.bill.modules.system.service.DeptService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

import jakarta.annotation.Resource;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author pengzheng
 */
//@ActiveProfiles("test")
@SpringBootTest
class DeptServiceTest {

    @Autowired
    private DeptService service;

    @Resource
    private DeptRepository repo;

    private Dept dept1;
    private Dept dept2;

    private Dept dept3;
    private Dept dept4;

    @BeforeEach
    void setUp() {
        dept1 = DeptBuilder.aDept().name("测试部门1").build();
        repo.insert(dept1);
        dept2 = DeptBuilder.aDept().name("测试部门2").pid(dept1.id).build();
        repo.insert(dept2);
        dept3 = DeptBuilder.aDept().name("测试部门3").pid(dept1.id).build();
        repo.insert(dept3);
        dept4 = DeptBuilder.aDept().name("测试部门4").pid(dept3.id).build();
        repo.insert(dept4);
    }

    @AfterEach
    void tearDown() {
        repo.delete(new QueryWrapper<>());
    }

    @Test
    void list() {
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        assertEquals(4, service.list(queryWrapper.like(Dept::getName, "测试部门")).size());
        assertEquals(1, service.list(queryWrapper.eq(Dept::getName, dept1.getName())).size());
        assertEquals(4, service.list().size());
    }

//    @Test
//    void listTree() {
//        Dept result = service.listTree(dept1.id);
//        int count = result.children.size();
//        assertEquals(2, count);
//    }

    @Test
    void createAdmin() {
        Dept dept  = new Dept();
        dept.setName("东菱经销商3");
        dept.setRemark("remark...");
        dept.setPid(dept1.id);
        service.save(dept);
        assertEquals(5, service.list().size());
        // 不能创建其他已存在的同名同代码部门
        assertThrows(DuplicateKeyException.class, () -> service.save(dept1));
    }

    @Test
    void create() {
//        Dept dept  = new Dept();
//        dept.setName("东菱经销商5");
//        dept.setRemark("remark...");
//        dept.setPid(dept1.id);
        DeptDto dto = new DeptDto();
        dto.setName("东菱经销商5");
        dto.setRemark("remark...");
        dto.setPid(dept1.id);
        DeptDto dto2 = new DeptDto();
        dto2.setName(dept2.getName());
        dto2.setRemark("remark...");
        dto2.setPid(dept1.id);
        Dept dept = service.create(dto);
        assertNotNull(dept.getId());
        assertEquals(5, service.list().size());
        // 不能创建其他已存在的同名同代码部门
        assertThrows(ConstraintException.class, () -> service.create(dto2));
    }

    @Test
    void updateAdmin() {
        DeptDto dto = new DeptDto();
        dto.setName("东菱经销商5");
        dto.setRemark("remark...");
        assertTrue(service.edit(dto, dept2.id));
        Dept tmp = service.getOne(new LambdaQueryWrapper<Dept>().eq(Dept::getName, dto.getName()));
        assertEquals(tmp.getName(), dto.getName());
        assertEquals(tmp.getId(), dept2.id);
        // 不能改为其他已存在的同名同代码部门
        assertThrows(ConstraintException.class,
                () -> service.edit(new DeptDto(dept3.getName(), "remark",null), dept2.id));
    }


//    @Test
//    void tree() {
//        Dept result = service.listTree(dept3.id);
//        HashSet<Long> deptPath = DeptTreeUtil.getDeptPath(result);
//        System.out.println(deptPath);
//        assertEquals(2, deptPath.size());
//    }

//    @Test
//    void findTreeChild() {
//        Dept result = service.listTree(dept1.id);
//        Dept childNode = DeptTreeUtil.getChildNode(result.children, dept2.id);
//        System.out.println(childNode.id);
//        assertEquals(dept2.id, childNode.id);
//    }


}
