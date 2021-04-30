package com.booktest.test.comment;


import com.booktest.test.CRUD.TownBook;
import com.booktest.test.user.User;
import lombok.Data;


@Data
public class CommentDto {

    private Long id;

    private String contents;

    private User user;

    private TownBook townBook;
}
