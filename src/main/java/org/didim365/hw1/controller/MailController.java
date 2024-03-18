package org.didim365.hw1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.didim365.hw1.service.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 아이디 찾기에 이용되는 controller
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;
    private final ObjectMapper objectMapper;

    /**
     * 아이디 찾기 -> 인증번호 받기 버튼 클릭 시 호출
     * @param userEmail 입력받은 이메일 주소
     * @return 보낸 메일의 결과를 나타내는 문자열을 반환
     * @throws JsonProcessingException JSON 데이터 처리 중 예외 처리
     */
    @ResponseBody
    @PostMapping("/mail")
    public String MailSend(@RequestBody String userEmail) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(userEmail);
        String email = jsonNode.get("email").asText();

        int number = mailService.sendMail(email);
        String num = "" + number;
        return num;
    }

    /**
     * 요청 본문에서 받은 인증 번호와 이메일을 사용하여 Redis에 저장된 인증 번호를 확인하고 결과를 반환
     *
     * @param verifications 사용자가 입력한 인증 번호와 이메일 정보를 담은 JSON 형식의 문자열
     * @return 인증 번호 확인 결과를 포함하는 ResponseEntity 객체를 반환
     * @throws JsonProcessingException JSON 데이터 처리 중 예외 처리
     */
    @PostMapping("/mail/check/verification-num")
    public ResponseEntity checkVerificationNumber(@RequestBody String verifications) throws JsonProcessingException{
        JsonNode jsonNode = objectMapper.readTree(verifications);
        String inputVerifiNum = jsonNode.get("inputNum").asText();
        String userEmail = jsonNode.get("userEmail").asText();

        return new ResponseEntity<>(mailService.checkVerficationNum(userEmail, inputVerifiNum), HttpStatus.OK);
    }

}
