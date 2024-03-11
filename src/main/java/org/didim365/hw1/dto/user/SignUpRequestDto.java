package org.didim365.hw1.dto.user;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import org.didim365.hw1.annotation.Password;

@Getter
public class SignUpRequestDto {
    @Email
    private String email;
    @Password
    private String password;
    private String name;

}
