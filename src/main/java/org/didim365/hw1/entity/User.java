package org.didim365.hw1.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.didim365.hw1.dto.user.SignUpRequestDto;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 로직에서 Entity 사용은 허용하고, 무분별한 객체 생성 제한
public class User {

    private String userId;
    private String memkey;
    private String password;
    private String name;
    private String signUpDate;
    private String insertTime;

    public void update(String password) {
        this.password = password;
    }

//    public User(SignUpRequestDto signupRequestDto) {
//        this.email = signupRequestDto.getEmail();
//        this.password = signupRequestDto.getPassword();
//        this.name = signupRequestDto.getName();
//    }


}
