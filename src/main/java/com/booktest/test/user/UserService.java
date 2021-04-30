package com.booktest.test.user;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;


//회원가입 서비스
    //userDto에 명시된 정보들을 새롭게 만들어 저장을합니다
    //회원가입과 동시에 카트도 동시에 생성합니다
    public void createUser(UserDto userDto){
        User user = new User(userDto);
        userRepository.save(user);


    }

//중복확인 서비스
    //CheckId에 중복확인성공시 반환하는 객체들이 있다. 중복된 아이디가 있을경우 falsㄷ를 이용하여 오류를 내주고 그렇지 않으면 가능하다고 성공시켜줍니다.
    public ReturnCheckId checkId(UserDto userDto){
        ReturnCheckId returnCheckId = new ReturnCheckId();
        Optional<User> member = userRepository.findByUsername(userDto.getUsername());
        if(member.isPresent()){
            returnCheckId.setOk(false);
            returnCheckId.setMsg("중복된 ID가 존재합니다.");
        } else{
            returnCheckId.setOk(true);
            returnCheckId.setMsg("사용 가능한 ID 입니다.");
        }
        return returnCheckId;
    }

//로그인 서비스
    //로그인 dto에 username과 password를 가지고 존재하는지 확인을 해줍니다 userrepository를 이용하여 db에서 체크
    //존재하지 않거나 비밀번호가 맞지 않을시 오류를 내주고 그렇지 않을경우 토큰을 발행합니다.
    public ReturnUser login(LoginDto loginDto) {
        ReturnUser returnUser = new ReturnUser();
        try {
            User member = userRepository.findByUsername(loginDto.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID 입니다."));
            if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
                throw new IllegalArgumentException("비밀번호를 다시 확인해 주세요.");
            }
            returnUser.setToken(jwtTokenProvider.createToken(member.getUsername()));
            returnUser.setUsername(member.getUsername());
            return returnUser;
        } catch (IllegalArgumentException e) {
            returnUser.setMsg(e.getMessage());
            return returnUser;
        }
    }
}
