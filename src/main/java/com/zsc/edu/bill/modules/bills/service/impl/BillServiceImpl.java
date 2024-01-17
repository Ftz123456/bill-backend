package com.zsc.edu.bill.modules.bills.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsc.edu.bill.modules.bills.dto.BillDto;
import com.zsc.edu.bill.modules.bills.entity.Bill;
import com.zsc.edu.bill.modules.bills.mapper.BillMapper;
import com.zsc.edu.bill.modules.bills.repo.BillRepository;
import com.zsc.edu.bill.modules.bills.service.BillService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
* @author yao
*/
@AllArgsConstructor
@Service
public class BillServiceImpl extends ServiceImpl<BillRepository, Bill> implements BillService {

    private final BillMapper mapper;

    @Override
    public Boolean create(BillDto dto) {
        Bill bill = mapper.toEntity(dto);
        bill.setUuid(UUID.randomUUID());
        return save(bill);
    }

    @Override
    public Boolean update(BillDto dto, Long id) {
        Bill bill = getById(id);
        mapper.convert(dto, bill);
        return updateById(bill);
    }
}




