package com.zsc.edu.bill.modules.system.mapper;

import com.zsc.edu.bill.common.mapstruct.BaseMapper;
import com.zsc.edu.bill.modules.system.dto.RoleDto;
import com.zsc.edu.bill.modules.system.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Yao
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends BaseMapper<RoleDto, Role> {
}
