package com.zsc.edu.bill.modules.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zsc.edu.bill.modules.system.dto.PageDto;
import com.zsc.edu.bill.modules.system.entity.Ticket;
import com.zsc.edu.bill.modules.system.query.TicketQuery;
import com.zsc.edu.bill.modules.system.query.UserQuery;
import com.zsc.edu.bill.modules.system.vo.TicketVo;

/**
* @author fantianzhi
* @description 针对表【ticket(票据表)】的数据库操作Service
* @createDate 2024-01-11 10:13:22
*/
public interface TicketService extends IService<Ticket> {

    PageDto<TicketVo> tickePage(TicketQuery query);
}
