package com.booktest.test.CRUD;

import com.booktest.test.comment.Comment;

import java.util.List;

public class DetailReturn {

    private Boolean ok;
    private TownBook townBook;
    private List<Comment> comments;
    private String msg;

    public DetailReturn(Boolean ok, TownBook townBook, List<Comment> comments, String msg){
        this.ok = ok;
        this.townBook=townBook;
        this.comments=comments;
        this.msg = msg;
    }
}
