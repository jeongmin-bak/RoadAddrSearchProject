package org.didim365.hw1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     *
     * @param key 입력받은 email
     * @param data 인증번호
     * @param duration 만료시간
     */
    public void setValues(String key, String data, Duration duration) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        values.set(key, data, duration);
    }

    /**
     *
     * @param key 입력받은 email
     * @return 레디스에 해당 값이 들어있는지 확인 / 만료되거나 값이 없다면 false
     */
    @Transactional(readOnly = true)
    public String getValues(String key) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        if (values.get(key) == null) {
            return "false";
        }
        return (String) values.get(key);
    }

    /**
     * 주어진 값이 "false"가 아닌지 확인
     *
     * @param value 이메일
     * @return 주어진 값이 "false"가 아니면 true를, 그렇지 않으면 false를 반환
     */
    public boolean checkExistsValue(String value) {
        return !value.equals("false");
    }
}
