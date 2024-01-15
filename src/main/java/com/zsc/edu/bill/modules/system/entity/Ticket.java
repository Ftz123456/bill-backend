package com.zsc.edu.bill.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 票据表
 * @TableName ticket
 */
@TableName(value ="ticket")
@Data
public class Ticket extends BaseEntity  implements Serializable{
    /**
     * 票据id 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 票据uuid 提供给前端显示用
     */
    private String uuid;

    /**
     * 用户id 票据创建者id
     */
    private Long userId;

    /**
     * 票据标题
     */
    private String title;

    /**
     * 主体内容 票据详细内容
     */
    private String body;

    /**
     * 金额 票据金额
     */
    private BigDecimal money;

    /**
     * 状态 0:未提交，草稿;1:未审核；2:审核通过；3:退回、审核未通过
     */
    private Integer status;

    /**
     * 票据类型
     */
    private Integer ticketType;

    /**
     * 联系方式email邮箱
     */
    private String contactEmail;

    /**
     * 客户公司名称 票据对应的企业名称
     */
    private String companyName;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Ticket other = (Ticket) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getBody() == null ? other.getBody() == null : this.getBody().equals(other.getBody()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getTicketType() == null ? other.getTicketType() == null : this.getTicketType().equals(other.getTicketType()))
            && (this.getContactEmail() == null ? other.getContactEmail() == null : this.getContactEmail().equals(other.getContactEmail()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getUpdatedBy() == null ? other.getUpdatedBy() == null : this.getUpdatedBy().equals(other.getUpdatedBy()))
            && (this.getUpdatedTime() == null ? other.getUpdatedTime() == null : this.getUpdatedTime().equals(other.getUpdatedTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getBody() == null) ? 0 : getBody().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getTicketType() == null) ? 0 : getTicketType().hashCode());
        result = prime * result + ((getContactEmail() == null) ? 0 : getContactEmail().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getCreatedBy() == null) ? 0 : getCreatedBy().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getUpdatedBy() == null) ? 0 : getUpdatedBy().hashCode());
        result = prime * result + ((getUpdatedTime() == null) ? 0 : getUpdatedTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uuid=").append(uuid);
        sb.append(", userId=").append(userId);
        sb.append(", title=").append(title);
        sb.append(", body=").append(body);
        sb.append(", money=").append(money);
        sb.append(", status=").append(status);
        sb.append(", ticketType=").append(ticketType);
        sb.append(", contactEmail=").append(contactEmail);
        sb.append(", companyName=").append(companyName);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}