package com.zsc.edu.bill.modules.system.mapper;

import com.zsc.edu.bill.common.mapstruct.BaseMapper;
import com.zsc.edu.bill.modules.system.dto.UserCreateDto;
import com.zsc.edu.bill.modules.system.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Yao
 */
@Mapper(componentModel = "spring",  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserCreateDto, User> {
}
