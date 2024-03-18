package org.didim365.hw1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.didim365.hw1.dto.user.FindUserRequestDto;
import org.didim365.hw1.dto.user.FindUserResponseDto;
import org.didim365.hw1.dto.user.SignUpRequestDto;
import org.didim365.hw1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j(topic = "UserController")
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
        log.info(signUpRequestDto.toString());
        userService.signup(signUpRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입에 성공하였습니다.");
    }

    @PostMapping("/duplication/id/check")
    public ResponseEntity checkdDuplicateId(@RequestBody String userId){
        return ResponseEntity.ok(userService.checkDuplicateId(userId));
    }

    @GetMapping("/help/idInquiry")
    public String findUserId(){
        return "finduserId";
    }

    @PostMapping("/find/userid")
    public ResponseEntity<FindUserResponseDto> findUserId(@RequestBody FindUserRequestDto findUserRequestDto){
        return ResponseEntity.ok(userService.findUserId(findUserRequestDto.getEmail(), findUserRequestDto.getName()));
    }

}
