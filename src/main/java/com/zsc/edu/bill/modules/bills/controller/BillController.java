package com.zsc.edu.bill.modules.bills.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsc.edu.bill.modules.bills.dto.BillDto;
import com.zsc.edu.bill.modules.audited.audit.Audit;
import com.zsc.edu.bill.modules.bills.entity.Bill;
import com.zsc.edu.bill.modules.bills.query.BillQuery;
import com.zsc.edu.bill.modules.bills.service.BillService;
import com.zsc.edu.bill.modules.bills.vo.BillVo;
import lombok.AllArgsConstructor;
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
    /**
     * 分页查询票据列表
     * @return 票据列表
     */
    @GetMapping
    public Page<Bill> list(BillQuery query, Page<Bill> page) {
        return service.page(page, query.wrapper());
    }
      /**
       * 创建票据
       * @return ture/false
       */
    @PostMapping
    public Boolean create(@RequestBody BillDto dto){
        return service.create(dto);
    }

    /**
     * 更新票据
     * @return ture/false
     */
    @PatchMapping("/{id}")
    public Boolean update(@RequestBody BillDto dto, @PathVariable("id") Long id){
        return service.update(dto, id);
    }

    /**
     * 删除票据
     * @return ture/false
     */
    @DeleteMapping
    public Boolean delete(Long id){
        return service.removeById(id);
    }

    /**
     * 批量删除票据
     * @return ture/false
     */
    @DeleteMapping("batch")
    public Boolean deleteBatch(List<Long> ids){
        return service.removeByIds(ids);
    }

    /**
     * 获取票据详情
     *
     * @return 票据详情
     */
    @GetMapping("{id}")
    public BillVo detail(@PathVariable Long id){
        return service.findById(id);
    }
    /*
    *审核票据
    **/
    @PatchMapping("audit/{id}")
    public Boolean audit(@PathVariable Long id, @RequestBody Audit audit){
        return service.audit(id, audit);
    }

}
