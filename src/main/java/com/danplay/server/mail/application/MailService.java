package com.danplay.server.mail.application;

import com.danplay.server.mail.dto.MailBooleanResponse;
import com.danplay.server.mail.dto.MailCodeRequest;
import com.danplay.server.mail.exception.MailException;
import com.danplay.server.user.domain.repository.UserRepository;
import com.danplay.server.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final RedisUtil redisUtil;

    private final UserRepository userRepository;

    public MailBooleanResponse sendVerificationMail(String mail) {
        if(invalidDankookEmail(mail)) { throw new MailException("올바르지 않은 메일 주소입니다!"); }
        if(userRepository.existsByEmail(mail)) { throw new MailException("존재하는 계정입니다!"); }

        SimpleMailMessage message = new SimpleMailMessage();
        final String code = createCode();

        message.setTo(mail);
        message.setSubject("[Danplay] 회원가입 이메일 인증코드 전송");
        message.setText(
                        "Danplay에 오신 것을 환영합니다!\n" +
                        "하단의 인증코드를 입력해주세요\n\n" +
                        "----------\n" +
                        code +
                        "\n----------"
        );
        javaMailSender.send(message); // 일반적인 예외를 처리해주고 있는 듯?

        redisUtil.setDataExpire(mail, code, 60000);

        return new MailBooleanResponse(true);
    }

    public MailBooleanResponse checkMailCode(MailCodeRequest mailCodeRequest) {
        if (Objects.equals(redisUtil.getData(mailCodeRequest.getMail()), mailCodeRequest.getCode())) {
            redisUtil.deleteData(mailCodeRequest.getMail());
            return new MailBooleanResponse(true);
        }
        throw new MailException("유효하지 않은 인증코드 입니다!");
    }

    public static String createCode() {
        int code = (int) (Math.random() * (999999 - 100000 + 1) + 100000);
        return Integer.toString(code);
    }

    public Boolean invalidDankookEmail(String mail) {
        return !Pattern.matches("[0-9]{8}@dankook.ac.kr", mail);
    }
}