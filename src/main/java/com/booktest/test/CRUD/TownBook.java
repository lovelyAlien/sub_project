package com.booktest.test.CRUD;

import com.booktest.test.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class TownBook extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "bookImg", nullable = true)
    private String bookImg;

    @Column(name = "description",nullable = true)
    private String description;

    @Column(name = "author", nullable = true)
    private String author;

    @Column(name = "price", nullable = true)
    private int price;

    @Column(name = "state", nullable = true)
    private String state;

    @Column(name = "category", nullable = true)
    private String category;

    public TownBook(TownBookDto townBookDto,User user){
        this.author = townBookDto.getAuthor();
        this.bookImg = townBookDto.getBookImg();
        this.category =townBookDto.getCategory();
        this.description =townBookDto.getDescription();
        this.state = townBookDto.getState();
        this.price = townBookDto.getPrice();
        this.user = user;


    }
    public void update(TownBookDto townBookDto){
        this.author = townBookDto.getAuthor();
        this.bookImg = townBookDto.getBookImg();
        this.category =townBookDto.getCategory();
        this.description =townBookDto.getDescription();
        this.state = townBookDto.getState();
        this.price = townBookDto.getPrice();

    }




}
