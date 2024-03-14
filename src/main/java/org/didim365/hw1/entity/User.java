package org.didim365.hw1.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.didim365.hw1.dto.user.SignUpRequestDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 로직에서 Entity 사용은 허용하고, 무분별한 객체 생성 제한
public class User {

    private String userId;
    private String custNo;
    private String password;
    private String name;
    private String signUpDate;
    private LocalDateTime insertTime;
    //private UserRole userRole = UserRole.USER;
    private String userRole = "users";

    public void update(String password) {
        this.password = password;
    }

    public User(SignUpRequestDto signupRequestDto) {
        this.userId = signupRequestDto.getUserId();
        this.custNo = createCustomerKey();
        this.password = signupRequestDto.getPassword();
        this.name = signupRequestDto.getName();
        this.signUpDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.insertTime = LocalDateTime.now();
    }

    public void updateEncodePwd(String encodePwd){
        this.password = encodePwd;
    }

    private String createCustomerKey(){
        int authNo = (int)(Math.random() * (99999 - 10000 + 1)) + 10000;
        return "J"+authNo;
    }


}
