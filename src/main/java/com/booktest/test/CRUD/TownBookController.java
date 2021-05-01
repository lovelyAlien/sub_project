package com.booktest.test.CRUD;


import com.booktest.test.comment.Comment;
import com.booktest.test.comment.CommentRepository;
import com.booktest.test.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class TownBookController {
    private final TownBookService townBookService;
    private final CommentRepository commentRepository;
    private final TownBookRepository townBookRepository;

    @GetMapping("/api/townbook")
    public ReturnTownBook getTownBook(){
        return townBookService.getTownBook();
    }

    @PostMapping("/api/townbook/{userid}")
    public ReturnTownBook createTownBook(@RequestBody TownBookDto townBookDto, @AuthenticationPrincipal User user){
        return townBookService.createTownBook(user, townBookDto);
    }

    @PutMapping("/api/townbook/{townBookId}")
    public TownBook updateTownBook(@PathVariable Long townBookId,@RequestBody TownBookDto townBookDto,@AuthenticationPrincipal User user){
        return townBookService.updateTownBook(townBookId,user, townBookDto);
    }

    @DeleteMapping("/api/townbook/{townBookId}")
    public ReturnTownBook deleteTownBook(@PathVariable Long townBookId,@AuthenticationPrincipal User user) {
        return townBookService.deleteTownBook(townBookId, user);
    }


    @GetMapping("/api/townBook/{townBookId}")
    public DetailReturn detail(@PathVariable Long townBookId) {
        TownBook townBook = townBookRepository.findById(townBookId).orElseThrow(
                ()-> new IllegalArgumentException("책이 존재하지 않습니다")
        );
        List<Comment> comments = commentRepository.findByTownBookId(townBookId);
        return new DetailReturn(true, townBook, comments, "성공!");
    }

}
