package com.booktest.test.CRUD;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ReturnTownBook {
    private boolean ok;
    private List<TownBook> townBookList = new ArrayList<>();
    private String msg;


    public ReturnTownBook(boolean ok, List<TownBook> townBookList) {
        this.ok = ok;
        this.townBookList= townBookList;
    }


}
