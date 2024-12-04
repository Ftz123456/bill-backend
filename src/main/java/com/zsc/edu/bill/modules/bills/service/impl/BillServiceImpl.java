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

import java.util.*;
import java.util.stream.Collectors;

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

        if (!dto.getSubmit()) {
            bill.setStatus(Bill.Status.SUBMIT);
            UserDetailsImpl userInfo = SecurityUtil.getUserInfo();
            bill.setUserId(userInfo.getId());
            bill.setUserName(userInfo.getUsername());
            return save(bill);

        }
        bill.setType(dto.getType());

        bill.setStatus(dto.getSubmit() ? Bill.Status.EXAMINE : Bill.Status.SUBMIT);
        UserDetailsImpl userInfo = SecurityUtil.getUserInfo();
        bill.setUserId(userInfo.getId());
        bill.setUserName(userInfo.getUsername());
        return  save(bill);
    }

    @Override
    public Boolean update(BillDto dto, Long id) {
        Bill bill = getById(id);
        mapper.convert(dto, bill);
        bill.setType(dto.getType());
        bill.setStatus(dto.getSubmit() ? Bill.Status.EXAMINE : Bill.Status.SUBMIT);
        return updateById(bill);
    }


    @Override
    public Boolean audit(Long id, BillDto dto) {
        Bill bill = getById(id);
        mapper.convert(dto, bill);
        bill.setStatus(dto.getSubmit() ? Bill.Status.PASS : Bill.Status.FAILED);
        bill.setType(dto.getType());
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
@Override
public Map<String, List<Object>> trendChart() {

        QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DATE_FORMAT(create_time,'%Y-%m-%d') as 'date',status,IFNULL(count(status),0) as 'number'")
                .groupBy("DATE_FORMAT(create_time,'%Y-%m-%d')")
                .groupBy("status")
                .orderByAsc("DATE_FORMAT(create_time,'%Y-%m-%d')");
//    listMaps(queryWrapper).stream().collect(Collectors.groupingBy(map -> map.get("date").toString())).entrySet().stream().map(entry -> {
//        Map<String,Map<String,Object>> map = new HashMap<>();
//        map.put(entry.getKey(),entry.getValue().stream().collect(Collectors.toMap(map1 -> map1.get("status").toString(), map1 -> map1.get("number"))));
//        return map;
//    }).collect(Collectors.toList());
        List<Map<String, Object>> rawData = listMaps(queryWrapper);

        List<Object> dates = new ArrayList<>();
        List<Integer> status0Counts = new ArrayList<>();
        List<Integer> status1Counts = new ArrayList<>();
        List<Integer> status2Counts = new ArrayList<>();
        List<Integer> status3Counts = new ArrayList<>();

        // 遍历原始数据填充列表
        for (Map<String, Object> data : rawData) {
            String date = (String) data.get("date");
            int status = Integer.parseInt(String.valueOf(data.get("status")));
            int number = Integer.parseInt(String.valueOf(data.get("number")));

            // 添加日期（如果尚未添加）
            if (!dates.contains(date)) {
                dates.add(date);
                // 为新日期初始化状态计数
                status0Counts.add(0);
                status1Counts.add(0);
                status2Counts.add(0);
                status3Counts.add(0);
            }

            // 根据状态更新相应的计数
            int dateIndex = dates.indexOf(date);
            switch (status) {
                case 0:
                    status0Counts.set(dateIndex, status0Counts.get(dateIndex) + number);
                    break;
                case 1:
                    status1Counts.set(dateIndex, status1Counts.get(dateIndex) + number);
                    break;
                case 2:
                    status2Counts.set(dateIndex, status2Counts.get(dateIndex) + number);
                    break;
                case 3:
                    status3Counts.set(dateIndex, status3Counts.get(dateIndex) + number);
                    break;
            }
        }


        // 将结果封装到Map中并返回
        Map<String, List<Object>> result = new HashMap<>();
        result.put("dates", dates);
    result.put("status0Counts", new ArrayList<Object>(status0Counts));
    result.put("status1Counts", new ArrayList<Object>(status1Counts));
    result.put("status2Counts", new ArrayList<Object>(status2Counts));
    result.put("status3Counts", new ArrayList<Object>(status3Counts));


   return result;
//将查询结果按日期分组，并将每个日期的数据按状态分组，返回一个list


//        return  listObjs(queryWrapper);
    }
}




