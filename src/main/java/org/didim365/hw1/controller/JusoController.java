package org.didim365.hw1.controller;

import lombok.RequiredArgsConstructor;
import org.didim365.hw1.dto.JusoRequestDto;
import org.didim365.hw1.dto.JusoResponseDto;
import org.didim365.hw1.service.JusoService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class JusoController {
    private final JusoService jusoService;

    @PostMapping("/juso/search/list")
    public List<JusoResponseDto> searchJuso(@RequestBody JusoRequestDto jusoRequestDto) throws IOException {
        return jusoService.searchJuso(jusoRequestDto.getJuso());
    }

    @GetMapping("/show/map")
    public String showMapPage(@RequestParam(name = "roadAddr") String roadAddr, Model model) {
        System.out.println("Received roadAddr value: " + roadAddr);

        model.addAttribute("roadAddr", roadAddr);
        // 뷰 이름을 반환하여 해당하는 HTML 파일을 찾아 클라이언트에게 전달합니다.
        return "showmap"; // 실제 HTML 파일의 이름에 맞게 수정해야 합니다.
    }
}
