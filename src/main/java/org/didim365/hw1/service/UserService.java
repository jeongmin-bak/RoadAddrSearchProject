package org.didim365.hw1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.didim365.hw1.dto.user.FindUserResponseDto;
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

    /**
     *
     * @param signupRequestDto 회원가입 폼에서 입력받은 아이디, 비밀번호, 이메일, 이름
     */
    public void signup(SignUpRequestDto signupRequestDto) {
        String encodedPwd = passwordEncoder.encode(signupRequestDto.getPassword());
        User user = new User(signupRequestDto, createCustomerKey());
        user.updateEncodePwd(encodedPwd);
        userMapper.insertUser(user);
    }

    /**
     *
     * @param userId 입력받은 아이디
     * @return 중복결과
     */
    public String checkDuplicateId(String userId) {
        userMapper.checkDuplicateId(userId).ifPresent((p) -> {
            throw new DuplicateUserIdException();
        });
        return "사용가능한 아이디 입니다.";
    }

    /**
     * 아이디찾기에서 사용
     * @param email 입력받은 이메일
     * @param name 입력받은 이름
     * @return 이메일,이름으로 조회한 아이디값 FindUserResponseDto
     */
    public FindUserResponseDto findUserId(String email, String name){
        log.info("userid = " + userMapper.findByUserId(email, name));
        return new FindUserResponseDto(userMapper.findByUserId(email, name));
    }

    /**
     * 회원번호 생성
     * @return 회원번호
     */
    private String createCustomerKey(){
        int authNo = (int)(Math.random() * (99999 - 10000 + 1)) + 10000;
        return "J"+authNo;
    }



}
