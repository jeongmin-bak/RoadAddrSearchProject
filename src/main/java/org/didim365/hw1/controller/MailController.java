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

@Slf4j
@Controller
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;
    private final ObjectMapper objectMapper;

    @ResponseBody
    @PostMapping("/mail")
    public String MailSend(@RequestBody String userEmail) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(userEmail);
        String email = jsonNode.get("email").asText();

        int number = mailService.sendMail(email);
        String num = "" + number;
        return num;
    }

    @PostMapping("/mail/check/verification-num")
    public ResponseEntity checkVerificationNumber(@RequestBody String verifications) throws JsonProcessingException{
        JsonNode jsonNode = objectMapper.readTree(verifications);
        String inputVerifiNum = jsonNode.get("inputNum").asText();
        String userEmail = jsonNode.get("userEmail").asText();
        log.info("inputNum = " + inputVerifiNum);
        log.info("userEmail = " + userEmail);

        return new ResponseEntity<>(mailService.checkVerficationNum(userEmail, inputVerifiNum), HttpStatus.OK);
    }

}
