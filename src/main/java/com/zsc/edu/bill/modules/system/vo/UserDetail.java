package com.zsc.edu.bill.modules.system.vo;

import com.zsc.edu.bill.modules.system.entity.Authority;
import com.zsc.edu.bill.modules.system.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

/**
 * @author ftz
 * 创建时间:16/2/2024 下午6:20
 * 描述: TODO
 */
@Getter
@Setter
public class UserDetail  {
    private User user;
    private List<String> authorities;
    private String permissions;
}
