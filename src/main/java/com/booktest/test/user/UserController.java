package com.booktest.test.user;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/api/signup")
    public void createUser(@RequestBody UserDto userDto) {
       userService.createUser(userDto);
    }

    // ID 중복 체크
    @PostMapping("/api/signup/checkid")
    public ReturnCheckId checkId(@RequestBody UserDto userDto){
        return userService.checkId(userDto);
    }

    // 로그인
    @PostMapping("/api/login")
    public ReturnUser login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }
}