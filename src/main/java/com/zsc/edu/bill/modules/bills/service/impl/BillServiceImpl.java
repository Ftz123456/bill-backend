package com.zsc.edu.bill.modules.bills.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsc.edu.bill.modules.bills.entity.Bill;
import com.zsc.edu.bill.modules.bills.repo.BillRepository;
import com.zsc.edu.bill.modules.bills.service.BillService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author yao
*/
@AllArgsConstructor
@Service
public class BillServiceImpl extends ServiceImpl<BillRepository, Bill> implements BillService {

}




