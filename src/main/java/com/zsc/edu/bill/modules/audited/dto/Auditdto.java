package com.zsc.edu.bill.modules.audited.dto;

import com.zsc.edu.bill.modules.audited.audit.Audit;
import lombok.Data;

/**
 * @author ftz
 * 创建时间:31/1/2024 下午6:47
 * 描述: TODO
 */
@Data
public class Auditdto {
    /*
     * 票据Id
     * */
    private Long ticketId;
    /*
     *审核员id
     * */
    private Long auditorId;
    /*
     *审核意见
     */
    private String comment;
    /*
     * 审核状态
     * */
    private Audit.Result result;
}
