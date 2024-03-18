package org.didim365.hw1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    /**
     * 로그인 후 보이는 화면
     * @return index html 파일
     */
    @GetMapping("/")
    public String Home(){
        return "index";
    }

    /**
     * 도로명 검색 클릭 시 보이는 화면
     * @return addrsearch(주소검색) html
     */
    @GetMapping("/juso/search")
    public String AddrSearch(){
        return "addrsearch";
    }


    /**
     * 도로명 검색 -> 검색 -> 검색 리스트 클릭 후 보이는 지도 화면
     * @return 검색 후 리스트 항목 클릭 시 지도 로드
     */
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

    /**
     * 사용자 검색 내역 관리 화면
     * @return 유저 검색 리스트 내역 html
     */
    @GetMapping("/juso/search/history")
    public String SearchAddrHis(){
        return "hissearchjuso";
    }



}
