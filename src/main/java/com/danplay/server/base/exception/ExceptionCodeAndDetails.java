package com.danplay.server.base.exception;

import com.danplay.server.auth.exception.InvalidAccessToken;
import com.danplay.server.auth.exception.InvalidRequestToken;
import com.danplay.server.mail.exception.DuplicateMailException;
import com.danplay.server.user.exception.InvalidFindPasswordException;
import com.danplay.server.mail.exception.InvalidMailCodeException;
import com.danplay.server.user.exception.InvalidPasswordException;
import com.danplay.server.user.exception.NonExistMailUserException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ExceptionCodeAndDetails {
    NOT_FOUND_API(HttpStatus.NOT_FOUND, "0002", "해당 경로에 대한 응답 API를 찾을 수 없습니다", NoHandlerFoundException.class),
    INVALID_REQUEST_TYPE(HttpStatus.BAD_REQUEST, "0003", "요청한 API에서 요구하는 형식에 맞지 않는 필드가 존재합니다", InvalidRequestTypeException.class),
    DUPLICATE_USER(HttpStatus.CONFLICT, "1001", "Danplay 서비스에 가입되어있는 계정입니다", DuplicateMailException.class),
    INVALID_MAIL_CODE(HttpStatus.CONFLICT, "1002", "유효하지 않은 인증코드 입니다", InvalidMailCodeException.class),
    NON_EXIST_MAIL_USER(HttpStatus.NOT_FOUND, "2001", "존재하지 않는 계정입니다", NonExistMailUserException.class),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "2002","비밀번호가 일치하지 않습니다", InvalidPasswordException.class),
    INVALID_FIND_PASSWORD(HttpStatus.UNAUTHORIZED, "2003", "서버에 저장된 사용자 정보와 일치하지 않습니다", InvalidFindPasswordException.class),
    INVALID_REQUEST_TOKEN(HttpStatus.UNAUTHORIZED, "3001", "인증 토큰이 요청 헤더에 존재하지 않습니다", InvalidRequestToken.class),
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "3002", "엑세스 토큰이 유효하지 않습니다", InvalidAccessToken.class);


    private final HttpStatus status;
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
