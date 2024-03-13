package org.didim365.hw1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.didim365.hw1.dto.user.SignUpRequestDto;
import org.didim365.hw1.entity.User;
import org.didim365.hw1.exception.user.DuplicateUserIdException;
import org.didim365.hw1.repository.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "UserService")
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    public void signup(SignUpRequestDto signupRequestDto) {
        String encodedPwd = passwordEncoder.encode(signupRequestDto.getPassword());
        User user = new User(signupRequestDto);
        user.updateEncodePwd(encodedPwd);

        log.info("password =" + encodedPwd);
        userMapper.findById(user.getUserId()).ifPresent((p) -> {
            throw new DuplicateUserIdException();
        });

        userMapper.insertUser(user);
    }

    public String checkDupliccateId(String userId) {
        userMapper.findById(userId).ifPresent((p) -> {
            throw new DuplicateUserIdException();
        });
        return "사용가능한 아이디 입니다.";
    }
}
