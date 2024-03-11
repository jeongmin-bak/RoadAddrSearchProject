package org.didim365.hw1.controller;

import lombok.RequiredArgsConstructor;
import org.didim365.hw1.dto.user.SignUpRequestDto;
import org.didim365.hw1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signUpPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignUpRequestDto signUpRequestDto){
        userService.signup(signUpRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입에 성공하였습니다.");
    }

}
