package com.zsc.edu.bill.modules.bills.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsc.edu.bill.framework.security.SecurityUtil;
import com.zsc.edu.bill.framework.security.UserDetailsImpl;
import com.zsc.edu.bill.modules.bills.dto.BillDto;
import com.zsc.edu.bill.modules.bills.entity.Bill;
import com.zsc.edu.bill.modules.bills.entity.Home;
import com.zsc.edu.bill.modules.bills.mapper.BillMapper;
import com.zsc.edu.bill.modules.bills.query.BillQuery;
import com.zsc.edu.bill.modules.bills.repo.BillRepository;
import com.zsc.edu.bill.modules.bills.service.BillService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Home getHomes(BillDto dto) {
        QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
        Home home = new Home();
        queryWrapper
                .select("status as 'status',IFNULL(count(status),0) as 'number'")
                .eq(Objects.nonNull(dto.getUserId()),"user_id",dto.getUserId())
                .eq(Objects.nonNull(dto.getAuditorId()),"auditor_id",dto.getAuditorId())
                .orderByAsc("status")
                .groupBy("status");
//       listMaps(queryWrapper).forEach(map ->
//               switch (map.get("status").toString()){
//                   case "0":
//                       home.setNotFiled((Integer) map.get("number"));
//
//                   case "1":
//                       home.setNotAudit((Integer)map.get("number"));
//
//                   case "2":
//                       home.setPass((Integer)map.get("number"));
//
//                   case "3":
//                       home.setNotPass((Integer)map.get("number"));
//
//                   default:
//                       throw new IllegalStateException("Unexpected value: " + map.get("status").toString());
//               }
//
//         );
        listMaps(queryWrapper).forEach(map -> {
            switch (map.get("status").toString()){
                case "0":
                    home.setNotFiled((Long) map.get("number"));
                    break;
                case "1":
                    home.setNotAudit((Long) map.get("number"));
                    break;
                case "2":
                    home.setPass((Long) map.get("number"));
                    break;
                case "3":
                    home.setNotPass((Long) map.get("number"));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + map.get("status").toString());
            }
        });



        return  home;
    }


}




