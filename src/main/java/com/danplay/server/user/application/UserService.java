package com.danplay.server.user.application;

import com.danplay.server.user.domain.entity.User;
import com.danplay.server.user.domain.repository.UserRepository;
import com.danplay.server.user.dto.SignUpRequest;
import com.danplay.server.user.dto.UserMapper;
import com.danplay.server.user.dto.UserResponse;
import com.danplay.server.user.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse signUp(SignUpRequest signUpRequest) {
        User user = UserMapper.INSTANCE.requestToUser(signUpRequest);
        checkDuplicatedUser(user.getEmail());
        // 이메일 인증 미완료
        user.setEmailAuthentication(false);
        User savedUser = userRepository.save(user);
        final UserResponse userResponse = UserMapper.INSTANCE.userToResponse(savedUser);
        return userResponse;
    }

    private void checkDuplicatedUser(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserException("이미 존재하는 유저입니다");
        }
    }
}
