package com.zsc.edu.bill.modules.bills.repo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsc.edu.bill.modules.bills.entity.Bill;
import com.zsc.edu.bill.modules.bills.vo.BillVo;
import org.apache.ibatis.annotations.Select;


/**
 * @author yao
 */
public interface BillRepository extends BaseMapper<Bill> {
    BillVo findById(Long id);

}
