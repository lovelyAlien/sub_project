package com.booktest.test.CRUD;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReturnMsg {
    private String msg;

    public ReturnMsg(String msg) {
        this.msg = msg;
    }
}
