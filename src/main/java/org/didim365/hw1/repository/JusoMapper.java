package org.didim365.hw1.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.didim365.hw1.dto.juso.JusoSearchHisDto;
import org.didim365.hw1.entity.SearchJuso;

import java.util.List;

@Mapper
public interface JusoMapper {
    int saveSearchAddr(SearchJuso searchJuso);
    List<JusoSearchHisDto> findUserSearchHistory(String memNb);
    Integer checkJusoSearchCount(String memNb, String searchAddr);
    void updateSearchCount(String memNb, String searchAddr);
    void deleteUserSearchHistory(String memNb, String searchAddr);
}
