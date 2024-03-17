package org.didim365.hw1.dto.juso;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JusoRequestDto {
    private String juso;
    private String jibunAddr;
    private String zipNo;
}
