package org.didim365.hw1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String Home(){
        return "index";
    }

    @GetMapping("/map/search")
    public String Search(){
        return "mapsearch";
    }

}
