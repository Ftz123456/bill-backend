package com.zsc.edu.bill.modules.audited.mapper;

import com.zsc.edu.bill.common.mapstruct.BaseMapper;
import com.zsc.edu.bill.modules.audited.audit.Audit;
import com.zsc.edu.bill.modules.audited.dto.Auditdto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author fantianzhi
* @description 针对表【audit(票据审核表 )】的数据库操作Mapper
* @createDate 2024-01-31 18:30:42
* @Entity com.zsc.edu.bill.modules.audited.audit.Audit
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuditMapper extends BaseMapper<Auditdto,Audit> {

}




