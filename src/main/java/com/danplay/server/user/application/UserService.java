package com.danplay.server.user.application;

import com.danplay.server.auth.token.TokenInfo;
import com.danplay.server.user.domain.entity.User;
import com.danplay.server.user.domain.repository.UserRepository;
import com.danplay.server.user.dto.SignUpRequest;
import com.danplay.server.user.dto.UserMapper;
import com.danplay.server.user.dto.UserResponse;
import com.danplay.server.user.exception.DuplicateLoginIdException;
import com.danplay.server.user.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse signUp(SignUpRequest signUpRequest) {

        if (userRepository.existsByLoginId(signUpRequest.getLoginId())) throw new DuplicateLoginIdException();

        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        final User user = UserMapper.INSTANCE.requestToUser(signUpRequest);
        final User savedUser = userRepository.save(user);

        UserResponse userResponse = UserMapper.INSTANCE.userToResponse(savedUser);
        // userResponse.setTokenResponse(jwtUtil.generateToken(getTokenInfo(savedUser)));

        return userResponse;
    }

    private TokenInfo getTokenInfo(User user) { // 이거 JwtUtil로 돌릴지
        return new TokenInfo(user.getId(), user.getMail(), user.getAuthority());
    }
}
