package com.zsc.edu.bill.common.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum EnableState implements IEnum<Boolean> {
    ENABLE(Boolean.TRUE),
    DISAENABLE(Boolean.FALSE);

    private boolean value;

    EnableState(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return this.value;
    }
}
