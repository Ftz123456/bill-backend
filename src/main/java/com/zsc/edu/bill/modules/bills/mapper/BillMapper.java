package com.zsc.edu.bill.modules.bills.mapper;



import com.zsc.edu.bill.common.mapstruct.BaseMapper;
import com.zsc.edu.bill.modules.bills.dto.BillDto;
import com.zsc.edu.bill.modules.bills.entity.Bill;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author yao
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BillMapper extends BaseMapper<BillDto, Bill> {

}




