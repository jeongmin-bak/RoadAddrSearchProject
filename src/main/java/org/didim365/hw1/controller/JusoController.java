package org.didim365.hw1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.didim365.hw1.dto.juso.JusoRequestDto;
import org.didim365.hw1.dto.juso.JusoResponseDto;
import org.didim365.hw1.dto.juso.JusoSearchHisDto;
import org.didim365.hw1.dto.juso.JusoSearchRequestDto;
import org.didim365.hw1.security.UserDetailsImpl;
import org.didim365.hw1.service.JusoService;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;

@Slf4j(topic = "JusoController")
@RestController
@RequiredArgsConstructor
public class JusoController {
    private final JusoService jusoService;

    @PostMapping("/juso/search/list")
    public List<JusoResponseDto> searchJuso(@RequestBody JusoRequestDto jusoRequestDto) throws IOException {
        log.info("jusoRequestDto = " + jusoRequestDto.getJuso());
        return jusoService.searchJuso(jusoRequestDto.getJuso());
    }

//    @PostMapping("/juso/search/list")
//    public List<JusoResponseDto> searchJuso(@RequestBody JusoRequestDto jusoRequestDto, @RequestParam(name = "pageNum") int pageNum, Model model) throws IOException {
//        log.info("jusoRequestDto = " + jusoRequestDto.getJuso());
//        log.info("totalCount = " + jusoService.getTotalCount(jusoRequestDto.getJuso()));
//        model.addAttribute("pageCount", jusoService.getTotalCount(jusoRequestDto.getJuso()));
//        return jusoService.searchJuso(jusoRequestDto.getJuso(), pageNum);
//    }

    @GetMapping("/show/map")
    public String showMapPage(@RequestParam(name = "roadAddr") String roadAddr, Model model) {
        log.info("Received roadAddr value: " + roadAddr);
        model.addAttribute("roadAddr", roadAddr);
        return "showmap";
    }

    @PostMapping("/juso/search/save")
    public void searchJusoSave(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody JusoSearchRequestDto jusoSearchRequestDto){
        String memNb = userDetails.getUser().getMemNb();
        jusoService.saveSearchJuso(memNb, jusoSearchRequestDto.getSearchAddr());
    }

    // 사용자 검색 이력 가져오기
    @GetMapping("/check/user/search/history")
    public List<JusoSearchHisDto> checkUserSearchHistory(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return jusoService.checkUserSearchHistory(userDetails.getUser().getMemNb());
    }
}
