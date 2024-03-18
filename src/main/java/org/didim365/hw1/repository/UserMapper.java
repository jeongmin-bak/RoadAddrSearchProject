package org.didim365.hw1.repository;

import org.apache.ibatis.annotations.Mapper;
import org.didim365.hw1.dto.user.LoginRequestDto;
import org.didim365.hw1.entity.User;

import java.util.Optional;

@Mapper
public interface UserMapper {
    /**
     *
     * @param email 사용자 아이디
     * @return 조회된 유저
     */
    Optional<User> checkDuplicateId(String email);

    /**
     *
     * @param user 회원가입하기 위한 유저 객체
     * @return 저장 성공여부
     */
    int insertUser(User user);

    /**
     *
     * @param userId 토큰검사를 위한 유저찾기
     * @return 유저
     */
    Optional<User> findByUser(String userId);

    /**
     * 아이디 찾기에서 입력받은 이메일, 이름
     * @param email 입력받은 이메일
     * @param name 입력받은 이름
     * @return 이메일과 이름으로 조회된 아이디
     */
    String findByUserId(String email, String name);
}
