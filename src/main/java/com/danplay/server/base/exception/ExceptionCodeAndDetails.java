package com.danplay.server.base.exception;

import com.danplay.server.mail.exception.DuplicateMailException;
import com.danplay.server.mail.exception.InvalidMailCodeException;
import com.danplay.server.mail.exception.InvalidMailTypeException;
import com.danplay.server.user.exception.DuplicateLoginIdException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ExceptionCodeAndDetails {
    NOT_FOUND_API("0002", "해당 경로에 대한 응답 API를 찾을 수 없습니다.", NoHandlerFoundException.class),
    INVALID_MAIL_TYPE("1001", "형식에 맞지 않는 이메일입니다", InvalidMailTypeException.class),
    DUPLICATE_USER("1002", "Danplay에 가입되어있는 계정입니다", DuplicateMailException.class),
    INVALID_MAIL_CODE("1003", "유효하지 않은 인증코드 입니다", InvalidMailCodeException.class),
    DUPLICATE_LOGINID("2001", "사용중인 아이디입니다", DuplicateLoginIdException.class);

    private final String code;
    private final String message;
    private final Class<? extends Exception> type;

    public static ExceptionCodeAndDetails findByClass(Class<? extends Exception> type) {
        return Arrays.stream(ExceptionCodeAndDetails.values())
                .filter(code -> code.type.equals(type))
                .findAny()
                .orElseThrow(NotFoundErrorCodeException::new);
    }
}
