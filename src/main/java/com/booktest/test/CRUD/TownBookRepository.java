package com.booktest.test.CRUD;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TownBookRepository extends JpaRepository<TownBook,Long> {

    List<TownBook> findAll();
    TownBook findByTownBookId(Long townBookId);

}
