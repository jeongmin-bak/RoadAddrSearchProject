package org.didim365.hw1.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchJuso {
    private String memNb;
    private String searchJuso;
    private int searchCount;
    private LocalDateTime insertTime;
    private LocalDateTime searchTime;

    public SearchJuso(String memNb, String searchJuso) {
        this.memNb = memNb;
        this.searchJuso = searchJuso;
        this.searchCount = 1;
        this.insertTime = LocalDateTime.now();
        this.searchTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "SearchJuso{" +
                "memNb='" + memNb + '\'' +
                ", searchJuso='" + searchJuso + '\'' +
                ", searchCount=" + searchCount +
                ", insertTime=" + insertTime +
                ", searchTime=" + searchTime +
                '}';
    }
}
