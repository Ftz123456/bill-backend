package com.zsc.edu.bill.modules.file.mapper;

import com.zsc.edu.bill.common.mapstruct.BaseMapper;
import com.zsc.edu.bill.modules.file.dto.AttachFileDto;
import com.zsc.edu.bill.modules.file.entity.AttachFile;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author fantianzhi
* @description 针对表【attach_file(票据附件表)】的数据库操作Mapper
* @createDate 2024-01-28 19:48:22
* @Entity com.zsc.edu.bill.modules.file.entity.AttachFile
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AttachFileMapper extends BaseMapper<AttachFileDto,AttachFile> {

}




