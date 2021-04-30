package com.booktest.test.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnCheckId {
    //중복확인아 완료되었을때 오류없을시 리턴
    private Boolean ok;
    private String msg;
}
