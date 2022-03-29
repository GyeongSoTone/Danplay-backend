package com.danplay.server.mail.application;

import com.danplay.server.mail.dto.MailBooleanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    public MailBooleanResponse sendVerificationMail(String mail) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mail);
        message.setSubject("[Danplay] 회원가입 이메일 인증코드 전송");
        message.setText(
                        "Danplay에 오신 것을 환영합니다!\n" +
                        "하단의 인증코드를 입력해주세요\n\n" +
                                "----------\n" +
                             createCode() +
                                "\n----------"
        );

        javaMailSender.send(message);

        return new MailBooleanResponse();
    }

    public static int createCode() {
        return (int) (Math.random() * (999999 - 100000 + 1) + 100000);
    }
}
