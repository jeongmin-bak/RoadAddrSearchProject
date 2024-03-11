package org.didim365.hw1.controller;

import lombok.RequiredArgsConstructor;
import org.didim365.hw1.service.UserService;
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

    @PostMapping("/signup")
    public String signup(){
        return "signup";
    }

}
