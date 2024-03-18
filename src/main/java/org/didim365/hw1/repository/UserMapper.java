package org.didim365.hw1.repository;

import org.apache.ibatis.annotations.Mapper;
import org.didim365.hw1.dto.user.LoginRequestDto;
import org.didim365.hw1.entity.User;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> checkDuplicateId(String email);
    int insertUser(User user);
    Optional<User> findByUser(String userId);
    String findByUserId(String email, String name);
}
