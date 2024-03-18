package org.didim365.hw1.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.didim365.hw1.dto.juso.JusoSearchHisDto;
import org.didim365.hw1.entity.SearchJuso;

import java.util.List;

@Mapper
public interface JusoMapper {
    /**
     *
     * @param searchJuso 검색한 주소 값
     * @return 저장 성공여부
     */
    int saveSearchAddr(SearchJuso searchJuso);

    /**
     *
     * @param memNb 회원번호
     * @return 회원번호로 조회된 검색이력 리스트
     */
    List<JusoSearchHisDto> findUserSearchHistory(String memNb);

    /**
     *
     * @param memNb 회원번호
     * @param searchAddr 도로명 주소
     * @return 도로명 주소 검색 횟수
     */
    Integer checkJusoSearchCount(String memNb, String searchAddr);

    /**
     * 이미 검색했던 도로명 주소가 있다면 +1 update
     * @param memNb 회원번호
     * @param searchAddr 도로명 주소
     */
    void updateSearchCount(String memNb, String searchAddr);

    /**
     * 도로명 주소 검색 내역 삭제
     * @param memNb 회원번호
     * @param searchAddr 도로명 주소
     */
    void deleteUserSearchHistory(String memNb, String searchAddr);
}
