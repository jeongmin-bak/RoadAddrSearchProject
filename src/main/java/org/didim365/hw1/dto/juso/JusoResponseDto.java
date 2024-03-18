package org.didim365.hw1.dto.juso;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JusoResponseDto {
    private String roadAddr;
    private String jibunAddr;
    private String zipNo;
    public JusoResponseDto(JusoRequestDto jusoRequestDto) {
        this.roadAddr = jusoRequestDto.getJuso();
        this.jibunAddr = jusoRequestDto.getJibunAddr();
        this.zipNo = jusoRequestDto.getZipNo();
    }
}
