package com.danplay.server.mail.application;

import com.danplay.server.mail.dto.MailCodeRequest;
import com.danplay.server.mail.dto.MailResponse;
import com.danplay.server.mail.exception.DuplicateMailException;
import com.danplay.server.user.exception.InvalidFindPasswordException;
import com.danplay.server.mail.exception.InvalidMailCodeException;
import com.danplay.server.user.domain.entity.User;
import com.danplay.server.user.domain.repository.UserRepository;
import com.danplay.server.user.exception.NonExistMailUserException;
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

    public MailResponse sendVerificationMail(final String mail) {

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
        javaMailSender.send(message);

        redisUtil.setDataExpire(mail, code, 60000);

        return new MailResponse("해당 이메일로 인증코드가 전송되었습니다", mail, false);
    }

    public MailResponse checkMailCode(MailCodeRequest mailCodeRequest) {

        if (!Objects.equals(redisUtil.getData(mailCodeRequest.getMail()), mailCodeRequest.getCode()))
            throw new InvalidMailCodeException();

        redisUtil.deleteData(mailCodeRequest.getMail());

        return new MailResponse("이메일 인증이 완료되었습니다", mailCodeRequest.getMail(), true);
    }

    public void sendPassword(String mail, String password) {

        SimpleMailMessage message = new SimpleMailMessage();
        final String code = createCode();

        message.setTo(mail);
        message.setSubject("[Danplay] 재설정된 비밀번호 전송");
        message.setText(
                "Danplay에 오신 것을 환영합니다!\n" +
                        "하단의 재설정된 비밀번호를 전송해드립니다\n" +
                        "로그인 후, '비밀번호 변경하기'를 통해 사용하실 비밀번호로 수정이 가능합니다\n\n" +
                        "-------------------------\n" +
                        password +
                        "\n-------------------------"
        );
        javaMailSender.send(message);

        redisUtil.setDataExpire(mail, code, 60000);
    }

    private String createCode() {
        int code = (int) (Math.random() * (999999 - 100000 + 1) + 100000);
        return Integer.toString(code);
    }
}
