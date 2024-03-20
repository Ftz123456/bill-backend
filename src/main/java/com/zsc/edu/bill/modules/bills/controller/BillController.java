package com.zsc.edu.bill.modules.bills.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsc.edu.bill.framework.security.SecurityUtil;
import com.zsc.edu.bill.framework.security.UserDetailsImpl;
import com.zsc.edu.bill.modules.bills.dto.BillDto;
import com.zsc.edu.bill.modules.bills.entity.Bill;
import com.zsc.edu.bill.modules.bills.entity.Home;
import com.zsc.edu.bill.modules.bills.query.BillQuery;
import com.zsc.edu.bill.modules.bills.service.BillService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
     * 首页数据
     * <p>
     */
    @GetMapping("home")
    public Home home(BillDto dto){
        return service.getHomes(dto);
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
    public Bill detail(@PathVariable Long id){
        return service.getById(id);
    }
    /*
    *审核票据
    **/
    @PatchMapping("audit/{id}")
    @PreAuthorize("hasAuthority('BILL_AUDIT')")
    public Boolean audit(@PathVariable Long id, @RequestBody BillDto dto){
        return service.audit(id, dto);
    }

    /**
     *审核人个人审核列表
     */
    @GetMapping("audit/list")
    @PreAuthorize("hasAuthority('BILL_QUERY')")
    public Page<Bill> list1(BillQuery query, Page<Bill> page) {
        UserDetailsImpl userInfo = SecurityUtil.getUserInfo();
        query.setAuditorId(userInfo.getId());
        return service.auditPage(page, query);
    }


}
