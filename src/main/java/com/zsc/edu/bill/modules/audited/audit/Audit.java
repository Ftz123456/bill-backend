package com.zsc.edu.bill.modules.audited.audit;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.zsc.edu.bill.modules.system.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author ftz
 * 创建时间:30/1/2024 下午8:58
 * 描述: TODO
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="Audit")
@Data
public class Audit extends BaseEntity {
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
    private Result result;
    @Getter
    public enum Result{
        PASS(2, "审核通过"),
        FAILED(3, "审核未通过");
        @EnumValue
        private final int code;

        @JsonValue
        private final String name;

        Result(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public static Result get(String name) {
            for (Result item : values()) {
                if (item.getName() .equals(name)) {
                    return item;
                }
            }
            return null;
        }

    }



}
