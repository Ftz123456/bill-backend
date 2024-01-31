package com.zsc.edu.bill.modules.bills.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsc.edu.bill.modules.bills.dto.BillDto;
import com.zsc.edu.bill.modules.audited.audit.Audit;
import com.zsc.edu.bill.modules.bills.entity.Bill;
import com.zsc.edu.bill.modules.bills.mapper.BillMapper;
import com.zsc.edu.bill.modules.bills.repo.BillRepository;
import com.zsc.edu.bill.modules.bills.service.BillService;
import com.zsc.edu.bill.modules.bills.vo.BillVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
* @author yao
*/
@AllArgsConstructor
@Service
public class BillServiceImpl extends ServiceImpl<BillRepository, Bill> implements BillService {

    private final BillMapper mapper;
    private final BillRepository repository;

    @Override
    public Boolean create(BillDto dto) {
        Bill bill = mapper.toEntity(dto);
        bill.setUuid(UUID.randomUUID().toString());
        return save(bill);
    }

    @Override
    public Boolean update(BillDto dto, Long id) {
        Bill bill = getById(id);
        mapper.convert(dto, bill);
        return updateById(bill);
    }

    @Override
    public BillVo findById(Long id) {
        Bill bill = getById(id);

        BillVo vo = new BillVo();





        return repository.findById(id);
    }

    @Override
    public Boolean audit(Long id, Audit audit) {
        Bill bill = getById(id);
        if (bill != null) {
           bill.setStatus(Bill.Status.valueOf(audit.getResult().getName()));
            return updateById(bill);
        }
//        Audit audit1 =
        return null;
    }
}




