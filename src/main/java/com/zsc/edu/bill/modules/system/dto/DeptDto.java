package com.zsc.edu.bill.modules.system.dto;

import com.zsc.edu.bill.modules.system.entity.Dept;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 部门Dto
 *
 * @author harry_yao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptDto {

    /**
     * 编码
     */
//    @NotBlank(message = "编码不能为空")
//    public String code;

    /**
     * 名称
     */
    @NotBlank(message = "名字不能为空")
    public String name;

    /**
     * 备注
     */
    public String remark;

    /**
     * 父部门ID
     */
    @NotNull(message = "上级公司不能为空")
    public Long pid;

    public LambdaUpdateWrapper<Dept> updateWrapper(Long id) {
        LambdaUpdateWrapper<Dept> updateWrapper = new LambdaUpdateWrapper<>();
        return updateWrapper.eq(Dept::getId, id)
                .set(Dept::getName, name)
                .set(StringUtils.hasText(remark), Dept::getRemark, remark);
    }

}
