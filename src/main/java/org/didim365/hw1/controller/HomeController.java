package org.didim365.hw1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/")
    public String Home(){
        return "index";
    }

    @GetMapping("/address/search/map")
    public String Search(@RequestParam(name="roadAddr")String roadAddr, Model model){
        model.addAttribute("roadAddr", roadAddr);
        return "showmap";
    }

    @GetMapping("/address/search")
    public String AddrSearch(){
        return "addrsearch";
    }


}
