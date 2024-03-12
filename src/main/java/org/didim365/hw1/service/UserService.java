package org.didim365.hw1.service;

import lombok.RequiredArgsConstructor;
import org.didim365.hw1.dto.user.SignUpRequestDto;
import org.didim365.hw1.entity.User;
import org.didim365.hw1.exception.user.DuplicateEmailException;
import org.didim365.hw1.repository.UserMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    //private final PasswordEncoder passwordEncoder;
    public void signup(SignUpRequestDto signupRequestDto) {
        //String encodedPwd = passwordEncoder.encode(signupRequestDto.getPassword());
        //User user = new User(signupRequestDto);
        //user.update(encodedPwd);

//        userMapper.findByEmail(user.getEmail()).ifPresent((p) -> {
//            throw new DuplicateEmailException();
//        });

        //userRepository.save(user);
    }
}
