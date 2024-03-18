package org.didim365.hw1.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MailService {
    private static final String AUTH_CODE_PREFIX = "AuthCode";
    private final JavaMailSender javaMailSender;
    private final RedisService redisService;
    private static final String senderEmail = "jmpark@didim365.com";

    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;
    private static int number;

    public static void createNumber(){
        number = (int)(Math.random() * (90000)) + 100000;
    }

    public MimeMessage CreateMail(String mail){
        createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("아이디 찾기 이메일 인증");
            log.info("인증번호 : " + number);
            String body = "";
            body += "<h3>" + "아이디 찾기 인증번호 : " + number + "</h3>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");

            redisService.setValues(AUTH_CODE_PREFIX + mail,
                    String.valueOf(number), Duration.ofMillis(this.authCodeExpirationMillis));


        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }

    public int sendMail(String mail){
        MimeMessage message = CreateMail(mail);
        javaMailSender.send(message);
        return number;
    }

    public boolean checkVerficationNum(String mail, String inputVerifiNum){
        String redisAuthCode = redisService.getValues(AUTH_CODE_PREFIX + mail);
        log.info("redisAuthCode = " + redisAuthCode);
        boolean authResult = redisService.checkExistsValue(redisAuthCode) && redisAuthCode.equals(inputVerifiNum);
        log.info("authResult = " + authResult);
        return authResult;
    }

}

