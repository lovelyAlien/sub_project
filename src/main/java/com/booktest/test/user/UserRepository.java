package com.booktest.test.user;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {
    //회원가입시 중복된 아이디가 있는지 검사하는 절차
    Optional<User> findByUsername(String username);
    //로그인시 존재하는 id인지 확인하는절차
    Optional<User> findByUserId (Long userid);
}