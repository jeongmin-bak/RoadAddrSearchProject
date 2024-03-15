package org.didim365.hw1.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.didim365.hw1.dto.user.SignUpRequestDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 로직에서 Entity 사용은 허용하고, 무분별한 객체 생성 제한
public class User {

    private String userId;
    private String memNb;
    private String password;
    private String name;
    private String signUpDate;
    private LocalDateTime insertTime;
    private String userRole = "users";

    public void update(String password) {
        this.password = password;
    }

    public User(SignUpRequestDto signupRequestDto, String memNb) {
        this.userId = signupRequestDto.getUserId();
        this.memNb = memNb;
        this.password = signupRequestDto.getPassword();
        this.name = signupRequestDto.getName();
        this.signUpDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.insertTime = LocalDateTime.now();
    }

    public void updateEncodePwd(String encodePwd){
        this.password = encodePwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", memNb='" + memNb + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", signUpDate='" + signUpDate + '\'' +
                ", insertTime=" + insertTime +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
