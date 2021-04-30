package com.booktest.test.user;

import com.booktest.test.CRUD.Timestamped;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@Table(name = "USER")
public class User extends Timestamped implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore // pw json에서 숨김처리
    @Column(nullable = false)
    private String password;

    @Column
    private String email;

    @JsonIgnore
    @Override //계정이 가지고 있는 권한 목록들을 리턴
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override //계정의 만료여부 리턴 스프링시큐리티의 기능들
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override//계정의 잠금여부를 리턴
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override//계정의 비번이 만료되었는지 리턴
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override//사용가능한계정인지 리턴
    public boolean isEnabled() {
        return true;
    }

    @Builder //비밀번호 인코딩
    public User(UserDto userDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        this.username = userDto.getUsername();
        this.password = passwordEncoder.encode(userDto.getPassword());
        this.email = userDto.getEmail();
    }



}