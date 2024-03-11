package org.didim365.hw1.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.didim365.hw1.dto.user.SignUpRequestDto;

@Getter
@Table(name="users")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 로직에서 Entity 사용은 허용하고, 무분별한 객체 생성 제한
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    public void update(String password) {
        this.password = password;
    }

    public User(SignUpRequestDto signupRequestDto) {
        this.email = signupRequestDto.getEmail();
        this.password = signupRequestDto.getPassword();
        this.name = signupRequestDto.getName();

    }


}
