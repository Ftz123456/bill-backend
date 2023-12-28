package com.zsc.edu.bill.modules.system.query;

import com.zsc.edu.bill.modules.system.entity.Dept;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;


/**
 * 部门Query
 *
 * @author Yao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptQuery {

    /**
     * 编码，前缀匹配
     */
    public String code;

    /**
     * 名称，模糊查询
     */
    public String name;

    public LambdaQueryWrapper<Dept> wrapper() {
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(this.name), Dept::getName, this.name);
        return queryWrapper;
    }

}
