package org.didim365.hw1.controller;

import lombok.RequiredArgsConstructor;
import org.didim365.hw1.dto.JusoRequestDto;
import org.didim365.hw1.dto.JusoResponseDto;
import org.didim365.hw1.service.JusoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
