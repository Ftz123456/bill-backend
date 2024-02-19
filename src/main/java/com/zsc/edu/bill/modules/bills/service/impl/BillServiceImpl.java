package com.zsc.edu.bill.modules.bills.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsc.edu.bill.framework.security.SecurityUtil;
import com.zsc.edu.bill.framework.security.UserDetailsImpl;
import com.zsc.edu.bill.modules.audited.audit.Audit;
import com.zsc.edu.bill.modules.audited.dto.Auditdto;
import com.zsc.edu.bill.modules.audited.repo.AuditRepository;
import com.zsc.edu.bill.modules.audited.service.AuditService;
import com.zsc.edu.bill.modules.bills.dto.BillDto;
import com.zsc.edu.bill.modules.bills.entity.Bill;
import com.zsc.edu.bill.modules.bills.mapper.BillMapper;
import com.zsc.edu.bill.modules.bills.query.BillQuery;
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
    private final AuditRepository auditRepository;
    private final AuditService auditService;

    @Override
    public Boolean create(BillDto dto) {
        Bill bill = mapper.toEntity(dto);
        String uuid=UUID.randomUUID().toString();
        bill.setUuid(uuid);
        if (dto.getSubmit()) {
            bill.setStatus(Bill.Status.EXAMINE);
        }else {
        bill.setStatus(Bill.Status.SUBMIT);}
        if (dto.getUserId()==null){
            UserDetailsImpl userInfo = SecurityUtil.getUserInfo();
            bill.setUserId(userInfo.getId());
        }
        save(bill);
        Bill one = repository.selectOne(new LambdaQueryWrapper<Bill>().eq(Bill::getUuid, uuid));
        Audit audit = new Audit();
        audit.setTicketId(one.getId());
        audit.setAuditorId(dto.getAuditorId());
        return auditService.save(audit);
    }

    @Override
    public Boolean update(BillDto dto, Long id) {
        Bill bill = getById(id);
        mapper.convert(dto, bill);
        if (dto.getAuditorId()!=null) {
            Audit audit = auditService.getOne(new LambdaQueryWrapper<Audit>().eq(Audit::getTicketId, id));
            audit.setAuditorId(dto.getAuditorId());
            auditService.updateById(audit);
            }
        return updateById(bill);
    }

    @Override
    public BillVo findById(Long id) {
        Bill bill = getById(id);

        BillVo vo = new BillVo();
        Audit audit = auditRepository.selectOne(new LambdaQueryWrapper<Audit>().eq(Audit::getTicketId, id));
        vo.setBill(bill);
        vo.setAudit(audit);

        return vo;
    }

    @Override
    public Boolean audit(Long id, Auditdto audit) {
        Bill bill = getById(id);
        Audit audit1 = auditRepository.selectById(audit.getId());
        audit1.setComment( audit.getComment());
        if ("PASS".equals(audit.getResult())) {
            bill.setStatus(Bill.Status.PASS);
            audit1.setResult(Audit.Result.PASS);
        }else if ("FAILED".equals(audit.getResult())) {
            bill.setStatus(Bill.Status.FAILED);
            audit1.setResult(Audit.Result.FAILED);

        }if (bill!=null ) {
          updateById(bill);
        }
        auditService.updateById(audit1);
        return auditService.updateById(audit1);
    }
/**
 * 审核员分页
 */

    @Override
    public Page<Bill> auditPage(Page<Bill> page, BillQuery query) {
        UserDetailsImpl userInfo = SecurityUtil.getUserInfo();
        LambdaQueryWrapper<Bill> wrappered = query.wrapper();
        wrappered.ne(Bill::getStatus, Bill.Status.SUBMIT);
        wrappered.inSql(Bill::getId, "select ticket_id from audit where auditor_id = " + userInfo.getId());
    return  repository.selectPage(page, wrappered);
    }


}




