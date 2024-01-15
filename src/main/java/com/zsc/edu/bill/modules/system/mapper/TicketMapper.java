package com.zsc.edu.bill.modules.system.mapper;



import com.zsc.edu.bill.common.mapstruct.BaseMapper;
import com.zsc.edu.bill.modules.system.dto.TicketDto;
import com.zsc.edu.bill.modules.system.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author fantianzhi
* @description 针对表【ticket(票据表)】的数据库操作Mapper
* @createDate 2024-01-11 10:13:22
* @Entity com.zsc.edu.bill.modules.system.entity.Ticket
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper extends BaseMapper<TicketDto,Ticket> {

}




