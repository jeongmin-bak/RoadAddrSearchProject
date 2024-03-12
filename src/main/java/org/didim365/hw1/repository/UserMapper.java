package org.didim365.hw1.repository;

import org.apache.ibatis.annotations.Mapper;
import org.didim365.hw1.entity.User;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> findByEmail(String email);
}
