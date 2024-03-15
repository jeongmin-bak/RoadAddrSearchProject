package org.didim365.hw1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.didim365.hw1.dto.user.LoginRequestDto;
import org.didim365.hw1.dto.user.SignUpRequestDto;
import org.didim365.hw1.entity.User;
import org.didim365.hw1.exception.user.DuplicateUserIdException;
import org.didim365.hw1.repository.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "UserService")
@RequiredArgsConstructor
public class UserService{
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    public void signup(SignUpRequestDto signupRequestDto) {
        String encodedPwd = passwordEncoder.encode(signupRequestDto.getPassword());
        User user = new User(signupRequestDto, createCustomerKey());
        user.updateEncodePwd(encodedPwd);
        userMapper.insertUser(user);
    }

    public String checkDupliccateId(String userId) {
        userMapper.findById(userId).ifPresent((p) -> {
            throw new DuplicateUserIdException();
        });
        return "사용가능한 아이디 입니다.";
    }

    private String createCustomerKey(){
        int authNo = (int)(Math.random() * (99999 - 10000 + 1)) + 10000;
        return "J"+authNo;
    }

}
