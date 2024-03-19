package com.zsc.edu.bill.modules.bills.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsc.edu.bill.modules.bills.dto.BillDto;
import com.zsc.edu.bill.modules.bills.entity.Bill;
import com.zsc.edu.bill.modules.bills.query.BillQuery;

import java.util.List;
import java.util.Map;

/**
* @author fantianzhi
* @description 针对表【ticket(票据表)】的数据库操作Service
* @createDate 2024-01-11 10:13:22
*/
public interface BillService extends IService<Bill> {

    Boolean create(BillDto dto);

    Boolean update(BillDto dto, Long id);



    Boolean audit(Long id, BillDto dto);


    Page<Bill> auditPage(Page<Bill> page, BillQuery query);

    List<Map<String, Object>> getHomes(BillDto dto);
}
