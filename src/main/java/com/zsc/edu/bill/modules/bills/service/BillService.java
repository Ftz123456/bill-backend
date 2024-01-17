package com.zsc.edu.bill.modules.bills.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsc.edu.bill.modules.bills.dto.BillDto;
import com.zsc.edu.bill.modules.bills.entity.Bill;
import com.zsc.edu.bill.modules.system.query.TicketQuery;
import com.zsc.edu.bill.modules.system.vo.TicketVo;

/**
* @author fantianzhi
* @description 针对表【ticket(票据表)】的数据库操作Service
* @createDate 2024-01-11 10:13:22
*/
public interface BillService extends IService<Bill> {

    Boolean create(BillDto dto);

    Boolean update(BillDto dto, Long id);
}
