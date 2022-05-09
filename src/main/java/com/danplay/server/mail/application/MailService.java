package com.danplay.server.mail.application;

import com.danplay.server.mail.dto.MailCodeRequest;
import com.danplay.server.mail.dto.MailStringResponse;
import com.danplay.server.mail.exception.DuplicateMailException;
import com.danplay.server.mail.exception.InvalidMailCodeException;
import com.danplay.server.user.domain.repository.UserRepository;
import com.danplay.server.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MailService {

    private final UserRepository userRepository;

    private final JavaMailSender javaMailSender;
    private final RedisUtil redisUtil;

    public MailStringResponse sendVerificationMail(final String mail) {

        if(userRepository.existsByMail(mail)) throw new DuplicateMailException();

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

        return new MailStringResponse("인증코드를 전송했습니다");
    }

    public MailStringResponse checkMailCode(MailCodeRequest mailCodeRequest) {

        if (!Objects.equals(redisUtil.getData(mailCodeRequest.getMail()), mailCodeRequest.getCode())) throw new InvalidMailCodeException();

        redisUtil.deleteData(mailCodeRequest.getMail());
        return new MailStringResponse("이메일 인증이 완료되었습니다");
    }

    public static String createCode() {
        int code = (int) (Math.random() * (999999 - 100000 + 1) + 100000);
        return Integer.toString(code);
    }
}
