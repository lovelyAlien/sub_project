package com.booktest.test.comment;

import com.booktest.test.CRUD.Timestamped;
import com.booktest.test.CRUD.TownBook;
import com.booktest.test.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable= false)
    private String username;

    @Column(nullable=false)
    private String contents;

    @ManyToOne
    @JoinColumn(name="USER_ID", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="town_book_id", nullable=false)
    private TownBook townBook;


    public Comment(CommentDto commentDto){
        this.username=commentDto.getUser().getUsername();
        this.contents=commentDto.getContents();
        this.user=commentDto.getUser();
        this.townBook=commentDto.getTownBook();
    }
    public void update(CommentDto commentDto){
        this.contents=commentDto.getContents();
    }
}
