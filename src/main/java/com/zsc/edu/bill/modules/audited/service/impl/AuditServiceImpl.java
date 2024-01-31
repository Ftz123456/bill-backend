package com.zsc.edu.bill.modules.audited.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsc.edu.bill.modules.audited.audit.Audit;
import com.zsc.edu.bill.modules.audited.repo.AuditRepository;
import com.zsc.edu.bill.modules.audited.service.AuditService;
import com.zsc.edu.bill.modules.audited.mapper.AuditMapper;
import org.springframework.stereotype.Service;

/**
* @author fantianzhi
* @description 针对表【audit(票据审核表 )】的数据库操作Service实现
* @createDate 2024-01-31 18:30:42
*/
@Service
public class AuditServiceImpl extends ServiceImpl<AuditRepository, Audit>
    implements AuditService{

}




