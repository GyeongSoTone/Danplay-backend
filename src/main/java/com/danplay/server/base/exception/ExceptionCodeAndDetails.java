package com.danplay.server.base.exception;

import com.danplay.server.mail.exception.DuplicateMailException;
import com.danplay.server.user.exception.InvalidFindPasswordException;
import com.danplay.server.mail.exception.InvalidMailCodeException;
import com.danplay.server.mail.exception.InvalidMailTypeException;
import com.danplay.server.user.exception.DuplicateLoginIdException;
import com.danplay.server.user.exception.InvalidPasswordException;
import com.danplay.server.user.exception.NonExistMailUserException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ExceptionCodeAndDetails {
    NOT_FOUND_API("0002", "해당 경로에 대한 응답 API를 찾을 수 없습니다", NoHandlerFoundException.class),
    INVALID_REQUEST_TYPE("0003", "요청한 API에서 요구하는 형식에 맞지 않는 필드가 존재합니다", InvalidRequestTypeException.class),
    INVALID_MAIL_TYPE("1001", "형식에 맞지 않는 메일입니다", InvalidMailTypeException.class),
    DUPLICATE_USER("1002", "Danplay 서비스에 가입되어있는 계정입니다", DuplicateMailException.class),
    INVALID_MAIL_CODE("1003", "유효하지 않은 인증코드 입니다", InvalidMailCodeException.class),
    NON_EXIST_MAIL_USER("2001", "존재하지 않는 계정입니다", NonExistMailUserException.class),
    INVALID_PASSWORD("2002","비밀번호가 일치하지 않습니다", InvalidPasswordException.class),
    INVALID_FIND_PASSWORD("2003", "서버에 저장된 사용자 정보와 일치하지 않습니다", InvalidFindPasswordException.class);

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
