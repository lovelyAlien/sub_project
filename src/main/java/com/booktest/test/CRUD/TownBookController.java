package com.booktest.test.CRUD;


import com.booktest.test.comment.Comment;
import com.booktest.test.comment.CommentRepository;
import com.booktest.test.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TownBookController {
    private final TownBookService townBookService;
    private final CommentRepository commentRepository;
    private final TownBookRepository townBookRepository;

    @GetMapping("/api/townBook")
    public ReturnTownBook getTownBook(){
        return townBookService.getTownBook();
    }

    @PostMapping("/api/townBook")
    public ReturnTownBook createTownBook(@RequestBody TownBookDto townBookDto, @AuthenticationPrincipal User user){
        return townBookService.createTownBook(user, townBookDto);
    }

    @PutMapping("/api/townBook/{townBookId}")
    public TownBook updateTownBook(@PathVariable Long townBookId,@RequestBody TownBookDto townBookDto,@AuthenticationPrincipal User user){
        return townBookService.updateTownBook(townBookId,user, townBookDto);
    }

    @DeleteMapping("/api/townBook/{townBookId}")
    public ReturnTownBook deleteTownBook(@PathVariable long townBookId,@AuthenticationPrincipal User user) {
        return townBookService.deleteTownBook(townBookId, user);
    }


//    @GetMapping("/api/townBook/{townBookId}")
//    public DetailReturn detail(@PathVariable long townBookId){
//
//            TownBook townBook =townBookRepository.findById(townBookId).orElseThrow(
//                    ()-> new IllegalArgumentException("책이 존재하지 않습니다")
//            );
//
//            List<Comment> comments =commentRepository.findAllByTownBookId(townBookId);
//            return new DetailReturn(true, townBook, comments, "성공!");
//
//    }

}
