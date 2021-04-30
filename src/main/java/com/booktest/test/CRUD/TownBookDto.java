package com.booktest.test.CRUD;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TownBookDto {
    private String username;
    private String bookImg;
    private String description;
    private String author;
    private int price;
    private String state;
    private String category;



}
