package com.danplay.server.auth.application;

import com.danplay.server.auth.exception.InvalidAccessToken;
import com.danplay.server.auth.exception.InvalidRequestToken;
import com.danplay.server.user.domain.entity.User;
import com.danplay.server.user.domain.repository.UserRepository;
import com.danplay.server.user.exception.NonExistMailUserException;
import com.danplay.server.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public String getTokenByHeader(HttpServletRequest httpServletRequest) {
        final String authHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) throw new InvalidRequestToken();
        System.out.println(authHeader);
        return authHeader.substring("Bearer ".length());
    }

    public User getUserByToken(HttpServletRequest httpServletRequest) {

        final String token = getTokenByHeader(httpServletRequest);
        User user = null;
        System.out.println(token);

        try {
            if (jwtUtil.isTokenExpired(token)) throw new InvalidAccessToken();

            user = userRepository.getUserByMail(jwtUtil.getEmailByToken(token)).orElseThrow(NonExistMailUserException::new);
        } catch (InvalidAccessToken invalidAccessToken) {}

        return user;
    }
}
