package org.didim365.hw1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.didim365.hw1.dto.juso.JusoRequestDto;
import org.didim365.hw1.dto.juso.JusoResponseDto;
import org.didim365.hw1.dto.juso.JusoSearchHisDto;
import org.didim365.hw1.dto.juso.JusoSearchRequestDto;
import org.didim365.hw1.security.UserDetailsImpl;
import org.didim365.hw1.service.JusoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j(topic = "JusoController")
@RestController
@RequiredArgsConstructor
public class JusoController {
    private final JusoService jusoService;

    /**
     * 도로명 검색 -> 검색창 주소입력 후 -> 검색 요청 -> 외부 API호출 결과 반환
     * @param jusoRequestDto 도로명, 지번, 우편 주소 가진 DTO 객체
     * @return 도로명, 지번, 우편 주소 담은 JusoResponseDto 객체의 리스트를 반환
     * @throws IOException 주소 검색 중 발생한 입출력 예외가 발생할 경우 처리
     */
    @PostMapping("/juso/search/list")
    public ResponseEntity<List<JusoResponseDto>> searchJuso(@RequestBody JusoRequestDto jusoRequestDto) throws IOException {
        log.info("jusoRequestDto = " + jusoRequestDto.getJuso());
        return ResponseEntity.ok(jusoService.searchJuso(jusoRequestDto.getJuso()));
    }

    /**
     * 검색 리스트 클릭 -> 지도 페이지 호출
     * 도로 주소를 이용하여 네이버 지도 페이지를 표시 화면 반환.
     *
     * @param  roadAddr 검색 리스트 중 하나 클릭 시 도로명 주소
     * @return 지도 페이지 뷰 ("showmap")
     */
    @GetMapping("/show/map")
    public String showMapPage(@RequestParam(name = "roadAddr") String roadAddr) {
        log.info("Received roadAddr value: " + roadAddr);
        return "showmap";
    }

    /**
     * 주소 검색 시 호출되는 함수
     * @param userDetails 사용자 인증 객체
     * @param jusoSearchRequestDto 도로명 주소 검색명이 담긴 DTO 객체
     */
    @PostMapping("/juso/search/save")
    public void searchJusoSave(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody JusoSearchRequestDto jusoSearchRequestDto){
        String memNb = userDetails.getUser().getMemNb();
        jusoService.saveSearchJuso(memNb, jusoSearchRequestDto.getSearchAddr());
    }

    /**
     * 검색 이력보기 버튼 클릭 시 호출
     * @param userDetails 사용자 인증정보 객체
     * @return 사용자의 주소 검색 기록을 담은 JusoSearchHisDto 객체의 리스트를 반환합니다.
     */
    @GetMapping("/check/user/search/history")
    public ResponseEntity<List<JusoSearchHisDto>>checkUserSearchHistory(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(jusoService.checkUserSearchHistory(userDetails.getUser().getMemNb()));
    }

    /**
     * 검색 이력보기 -> 리스트 중 기록삭제 버튼 클릭 시 호출
     * @param userDetails 사용자 인증정보 객체
     * @param jusoSearchRequestDto 회원번호, 검색했던 도로명 주소 DTO 객체
     */
    @PostMapping("/delete/juso/search/history")
    public void deleteUserSearchHistory(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody JusoSearchRequestDto jusoSearchRequestDto){
        jusoService.deleteUserSearchHistory(userDetails.getUser().getMemNb(), jusoSearchRequestDto.getSearchAddr());
    }
}
