package org.didim365.hw1.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.didim365.hw1.entity.SearchJuso;

@Mapper
public interface JusoMapper {
    int saveSearchAddr(SearchJuso searchJuso);
}
