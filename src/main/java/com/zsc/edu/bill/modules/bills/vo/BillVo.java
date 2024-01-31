package com.zsc.edu.bill.modules.bills.vo;

import com.zsc.edu.bill.modules.audited.audit.Audit;
import com.zsc.edu.bill.modules.bills.entity.Bill;
import lombok.Data;

/**
 * @author ftz
 * 创建时间:30/1/2024 下午8:35
 * 描述: TODO
 */
@Data
public class BillVo {
    Bill bill;
    Audit audit;



}
