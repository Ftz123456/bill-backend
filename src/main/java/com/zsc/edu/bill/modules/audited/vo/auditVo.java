package com.zsc.edu.bill.modules.audited.vo;

import com.zsc.edu.bill.modules.audited.audit.Audit;
import lombok.Data;

/**
 * @author ftz
 * 创建时间:5/2/2024 下午3:24
 * 描述:
 */
@Data
public class auditVo {
    private Long auditId;
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
    private String remark;
}
