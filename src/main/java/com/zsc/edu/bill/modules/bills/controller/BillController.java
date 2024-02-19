package com.zsc.edu.bill.modules.bills.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsc.edu.bill.modules.audited.audit.Audit;
import com.zsc.edu.bill.modules.audited.dto.Auditdto;
import com.zsc.edu.bill.modules.audited.service.AuditService;
import com.zsc.edu.bill.modules.bills.dto.BillDto;
import com.zsc.edu.bill.modules.bills.entity.Bill;
import com.zsc.edu.bill.modules.bills.query.BillQuery;
import com.zsc.edu.bill.modules.bills.service.BillService;
import com.zsc.edu.bill.modules.bills.vo.BillVo;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ftz
 * 票据Controller
 * 创建时间:11/1/2024 上午10:57
 * 描述: 针对表【ticket(票据表)】的数据库操作Controller
 */
@AllArgsConstructor
@RestController
@RequestMapping("api/rest/bill")
public class BillController {

    private final BillService service;
    private final AuditService auditService;
    /**
     * 分页查询票据列表
     * @return 票据列表
     */
    @GetMapping
    @PreAuthorize("hasAuthority('BILL_QUERY')")
    public Page<Bill> list(BillQuery query, Page<Bill> page) {
        return service.page(page, query.wrapper());
    }
      /**
       * 创建票据
       * @return ture/false
       */
    @PostMapping
    @PreAuthorize("hasAuthority('BILL_CREATE')")
    public Boolean create(@RequestBody BillDto dto){
        return service.create(dto);
    }

    /**
     * 更新票据
     * @return ture/false
     */
    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('BILL_UPDATE')")
    public Boolean update(@RequestBody BillDto dto, @PathVariable("id") Long id){
        return service.update(dto, id);
    }

    /**
     * 删除票据
     * @return ture/false
     */
    @DeleteMapping
    @PreAuthorize("hasAuthority('BILL_DELETE')")
    public Boolean delete(Long id){
        return service.removeById(id);
    }

    /**
     * 批量删除票据
     * @return ture/false
     */
    @DeleteMapping("batch")
    @PreAuthorize("hasAuthority('BILL_DELETE')")
    public Boolean deleteBatch(List<Long> ids){
        return service.removeByIds(ids);
    }

    /**
     * 获取票据详情
     *
     * @return 票据详情
     */
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('BILL_QUERY')")
    public BillVo detail(@PathVariable Long id){
        return service.findById(id);
    }
    /**
     * 选择审核人
     * */
    @PatchMapping("choose/{id}")
    @PreAuthorize("hasAuthority('BILL_CHOOSE_AUDITOR')")
    public Boolean audit(@PathVariable Long id, @RequestBody Long auditorId){
        Audit audit = new Audit();
        audit.setAuditorId(auditorId);
        audit.setTicketId(id);
        return auditService.save(audit);
    }

    /*
    *审核票据
    **/
    @PatchMapping("audit/{id}")@PreAuthorize("hasAuthority('BILL_AUDIT')")

    public Boolean audit(@PathVariable Long id, @RequestBody Auditdto audit){
        return service.audit(id, audit);
    }

    /**
     *审核人个人审核列表
     */
    @GetMapping("audit/list")
    @PreAuthorize("hasAuthority('BILL_QUERY')")
    public Page<Bill> list1(BillQuery query, Page<Bill> page) {
        return service.auditPage(page, query);
    }

}
