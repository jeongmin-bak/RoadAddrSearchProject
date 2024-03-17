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

    @GetMapping("/juso/search")
    public String AddrSearch(){
        return "addrsearch";
    }

    @GetMapping("/juso/search/map")
    public String Search(@RequestParam(name="roadAddr")String roadAddr,
                         @RequestParam(name="jibunAddr")String jibunAddr,
                         @RequestParam(name="zipNo") String zipNo,
                         Model model){
        model.addAttribute("roadAddr", roadAddr);
        model.addAttribute("jibunAddr", jibunAddr);
        model.addAttribute("zipNo", zipNo);
        return "showmap";
    }

    @GetMapping("/juso/search/history")
    public String SearchAddrHis(){
        return "hissearchjuso";
    }

}
