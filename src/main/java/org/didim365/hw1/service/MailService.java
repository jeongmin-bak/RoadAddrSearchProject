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

    /**
     * 랜덤 6자리 숫자를 생성
     */
    public static void createNumber(){
        number = (int)(Math.random() * (90000)) + 100000;
    }

    /**
     * 입력받은 이메일에 보낼 인증번호 메일 양식
     */
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

    /**
     * 입력된 이메일 주소로 메일을 전송하고, 전송된 메일의 인증 번호를 반환
     *
     * @param mail 전송할 이메일 주소
     * @return 전송된 메일의 인증 번호
     */
    public int sendMail(String mail){
        MimeMessage message = CreateMail(mail);
        javaMailSender.send(message);
        return number;
    }

    /**
     * 입력된 이메일과 인증 번호를 사용하여 인증 번호를 확인
     *
     * @param mail 이메일 주소
     * @param inputVerifiNum 입력된 인증 번호
     * @return 입력된 인증 번호와 일치하는지 여부를 반환
     */
    public boolean checkVerficationNum(String mail, String inputVerifiNum){
        String redisAuthCode = redisService.getValues(AUTH_CODE_PREFIX + mail);
        boolean authResult = redisService.checkExistsValue(redisAuthCode) && redisAuthCode.equals(inputVerifiNum);
        log.info("authResult = " + authResult);
        return authResult;
    }

}

