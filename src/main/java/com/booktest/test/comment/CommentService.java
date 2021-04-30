package com.booktest.test.comment;


import com.booktest.test.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;


    public void createComment(CommentDto commentDto){
        Comment comment=new Comment(commentDto);
        commentRepository.save(comment);

    }


    @Transactional
    public boolean updateComment(CommentDto commentDto){
        Long commentId =commentDto.getId();
        User user = commentDto.getUser();
        Comment comment =commentRepository.findById(commentId).orElseThrow(
                ()->new NullPointerException("해당 댓글이 존재하지 않습니다.")
        );

        if(comment.getUser().getUserId().equals((user.getUserId()))){
            comment.update(commentDto);
            return true;
        }
        else{
            return false;
        }
    }
}
