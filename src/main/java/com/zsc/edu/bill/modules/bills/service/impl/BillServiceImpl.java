package com.zsc.edu.bill.modules.bills.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsc.edu.bill.framework.security.SecurityUtil;
import com.zsc.edu.bill.framework.security.UserDetailsImpl;
import com.zsc.edu.bill.modules.bills.dto.BillDto;
import com.zsc.edu.bill.modules.bills.entity.Bill;
import com.zsc.edu.bill.modules.bills.mapper.BillMapper;
import com.zsc.edu.bill.modules.bills.query.BillQuery;
import com.zsc.edu.bill.modules.bills.repo.BillRepository;
import com.zsc.edu.bill.modules.bills.service.BillService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        bill.setStatus(dto.getSubmit() ? Bill.Status.EXAMINE : Bill.Status.SUBMIT);
        UserDetailsImpl userInfo = SecurityUtil.getUserInfo();
        bill.setUserId(userInfo.getId());
        return  save(bill);
    }

    @Override
    public Boolean update(BillDto dto, Long id) {
        Bill bill = getById(id);
        mapper.convert(dto, bill);
        return updateById(bill);
    }


    @Override
    public Boolean audit(Long id, BillDto dto) {
        Bill bill = getById(id);
        mapper.convert(dto, bill);
        return updateById(bill);
    }
/**
 * 审核员分页
 */

    @Override
    public Page<Bill> auditPage(Page<Bill> page, BillQuery query) {
        LambdaQueryWrapper<Bill> wrappered = query.wrapper();
        wrappered.ne(Bill::getStatus, Bill.Status.SUBMIT);
        return  repository.selectPage(page, wrappered);
    }

    @Override
    public List<Map<String, Object>> getHomes(BillDto dto) {
        QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("status as 'status',IFNULL(count(status),0) as 'number'")
                .eq(Objects.nonNull(dto.getUserId()),"user_id",dto.getUserId())
                .eq(Objects.nonNull(dto.getAuditorId()),"auditor_id",dto.getAuditorId())
                .orderByAsc("status")
                .groupBy("status");
        List<Map<String, Object>> maps = listMaps(queryWrapper);
        maps.forEach(map -> {
            Integer status = (Integer) map.get("status");
            map.put("status", Bill.Status.getByCode(status));
        });

        return  maps;
    }


}




