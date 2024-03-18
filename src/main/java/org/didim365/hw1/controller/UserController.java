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

    /**
     * 로그인 페이지에 접속할 때 호출
     * @return 로그인 페이지를 나타내는 뷰의 이름 ("login.html")
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 회원가입 페이지
     * @return 회원가입 뷰 이름("signup.html")
     */
    @GetMapping("/signup")
    public String signUpPage() {
        return "signup";
    }

    /**
     * signup.html에서 회원가입 버튼 클릭 시 호출
     * @param signUpRequestDto 회원가입 요청에 필요한 정보를 담고 있는 DTO 객체
     * @return 회원가입이 성공적으로 처리되었을 경우 201 Created 상태 코드와 메시지를 포함한 응답 엔터티를 반환
     */
    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignUpRequestDto signUpRequestDto){
        userService.signup(signUpRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입에 성공하였습니다.");
    }

    /**
     * signup.html에서 중복확인 버튼 클릭 시 호출
     * 아이디 중복 여부를 확인
     * @param userId 사용자가 확인하고자 하는 아이디
     * @return 아이디 중복 확인 결과를 담은 ResponseEntity 객체 반환
     */
    @PostMapping("/duplication/id/check")
    public ResponseEntity checkdDuplicateId(@RequestBody String userId){
        return ResponseEntity.ok(userService.checkDuplicateId(userId));
    }

    /**
     * 아이디 찾기 페이지
     * @return 아이디 찾기 페이지("finduserId")
     */
    @GetMapping("/help/idInquiry")
    public String findUserId(){
        return "finduserId";
    }

    /**
     * 아아디 찾기 버튼 클릭시 호출
     * @param findUserRequestDto 입력받은 이메일과 이름 정보를 담고 있는 DTO 객체
     * @return 찾은 아이디를 ResponseEntity 객체에 담아 반환
     */
    @PostMapping("/find/userid")
    public ResponseEntity<FindUserResponseDto> findUserId(@RequestBody FindUserRequestDto findUserRequestDto){
        return ResponseEntity.ok(userService.findUserId(findUserRequestDto.getEmail(), findUserRequestDto.getName()));
    }

}
