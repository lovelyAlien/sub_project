package com.booktest.test.CRUD;


import com.booktest.test.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TownBookService {

    private final TownBookRepository townBookRepository;

    public ReturnTownBook getTownBook(){
        List<TownBook> townBookList = townBookRepository.findAll();
        ReturnTownBook returnTownBook =new ReturnTownBook(true,townBookList);
        returnTownBook.setOk(true);
        returnTownBook.setTownBookList(townBookList);

        return returnTownBook;
    }

    public ReturnTownBook createTownBook(User user, TownBookDto townBookDto){
        String username = user.getUsername();
        townBookDto.setUsername(username);
        TownBook townBook = new TownBook(townBookDto,user);

        townBookRepository.save(townBook);
        ReturnTownBook returnMsg= new ReturnTownBook(true, getTownBook().getTownBookList());
        returnMsg.setOk(true);
        returnMsg.setMsg("동네서적 등록");
        return returnMsg;

    }
    @Transactional
    public TownBook updateTownBook(Long townBookId, User user, TownBookDto townBookDto){
        TownBook townBook = townBookRepository.findByTownBookId(townBookId);
        if(!townBook.getUser().getUserId().equals(user.getUserId())){
            throw new IllegalArgumentException("작성자만 수정가능합니다");
        }
        townBook.update(townBookDto);
        return townBook;
    }

    public ReturnTownBook deleteTownBook(Long townBookId,User user){
        TownBook townBook = townBookRepository.findByTownBookId(townBookId);
        if(!townBook.getUser().getUserId().equals(user.getUserId())){
            throw new IllegalArgumentException("작성자만 삭제가능합니다 ");
        }
        townBookRepository.deleteById(townBookId);
        ReturnTownBook returnMsg =new ReturnTownBook();
        returnMsg.setOk(true);
        returnMsg.setMsg("리뷰삭제완료");
        return returnMsg;
    }



}
