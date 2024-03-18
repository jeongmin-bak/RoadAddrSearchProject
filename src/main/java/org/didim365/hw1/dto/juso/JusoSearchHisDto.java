package org.didim365.hw1.dto.juso;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JusoSearchHisDto {
    String memNb;
    String searchJuso;
    int searchCount;
    String searchTime;

    public JusoSearchHisDto(String memNb, String searchJuso, int searchCount, String searchTime) {
        this.memNb = memNb;
        this.searchJuso = searchJuso;
        this.searchCount = searchCount;
        this.searchTime = searchTime;
    }
}
