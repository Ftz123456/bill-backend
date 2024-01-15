package com.zsc.edu.bill.modules.system.service.impl;


import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsc.edu.bill.modules.system.dto.PageDto;
import com.zsc.edu.bill.modules.system.entity.Ticket;
import com.zsc.edu.bill.modules.system.query.TicketQuery;
import com.zsc.edu.bill.modules.system.repo.TicketRepository;
import com.zsc.edu.bill.modules.system.service.TicketService;
import com.zsc.edu.bill.modules.system.vo.TicketVo;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author fantianzhi
* @description 针对表【ticket(票据表)】的数据库操作Service实现
* @createDate 2024-01-11 10:13:22
*/
@AllArgsConstructor
@Service
public class TicketServiceImpl extends ServiceImpl<TicketRepository, Ticket>
    implements TicketService {

    @Override
    public PageDto<TicketVo> tickePage(TicketQuery query) {

        Page<Ticket> page = Page.of(query.getPage(), query.getSize());
        if (query.getOrderBy()!=null) {
            page.addOrder(new OrderItem().setColumn(query.getOrderBy()).setAsc(query.getAsc()));
        } else {
            page.addOrder(new OrderItem().setColumn("id").setAsc(false));
        }
        Page<Ticket> ticketPage = lambdaQuery()
            .eq(query.getUuid()!=null, Ticket::getUuid, query.getUuid())
            .eq(query.getUserId()!=null, Ticket::getUserId, query.getUserId())
            .eq(query.getTitle()!=null, Ticket::getTitle, query.getTitle())
            .eq(query.getBody()!=null, Ticket::getBody, query.getBody())
            .eq(query.getMoney()!=null, Ticket::getMoney, query.getMoney())
            .eq(query.getStatus()!=null, Ticket::getStatus, query.getStatus())
            .eq(query.getTicketType()!=null, Ticket::getTicketType, query.getTicketType())
            .page(page);
        PageDto<TicketVo> pageDto = new PageDto<>();
        pageDto.setTotal(ticketPage.getTotal());
        pageDto.setPages(Math.toIntExact(ticketPage.getPages()));
        List<TicketVo> ticketVoList = ticketPage.getRecords().stream().map(ticket -> {
            TicketVo ticketVo = new TicketVo();
            BeanUtils.copyProperties(ticket, ticketVo);
            return ticketVo;
        }).toList();
        pageDto.setList(ticketVoList);
        return pageDto;
    }
}




