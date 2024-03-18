package org.didim365.hw1.dto.user;

import lombok.Getter;
import org.didim365.hw1.annotation.Password;

@Getter
public class SignUpRequestDto {
    private String userId;
    @Password
    private String password;
    private String name;
    private String email;
}
