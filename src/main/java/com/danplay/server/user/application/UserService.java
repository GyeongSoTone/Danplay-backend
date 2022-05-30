package com.danplay.server.user.application;

import com.danplay.server.auth.token.TokenInfo;
import com.danplay.server.user.domain.entity.PreferSport;
import com.danplay.server.user.domain.entity.User;
import com.danplay.server.user.domain.enumerations.Prefer;
import com.danplay.server.user.domain.repository.PreferSportRepository;
import com.danplay.server.user.domain.repository.UserRepository;
import com.danplay.server.user.dto.*;
import com.danplay.server.user.exception.DuplicateLoginIdException;
import com.danplay.server.user.exception.InvalidPasswordException;
import com.danplay.server.user.exception.NonExistMailUserException;
import com.danplay.server.user.exception.UserException;
import com.danplay.server.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PreferSportRepository preferSportRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse signUp(SignUpRequest signUpRequest) {

        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        final User user = UserMapper.INSTANCE.reqToE(signUpRequest);
        final User savedUser = userRepository.save(user);
        UserResponse userResponse = UserMapper.INSTANCE.eToRes(savedUser);

        List<PreferSport> preferSports = new ArrayList<>();
        for (Prefer prefer : signUpRequest.getPreferSport())
            preferSports.add(PreferSport.builder().user(savedUser).prefer(prefer).build());
        preferSportRepository.saveAll(preferSports);

        userResponse.setTokenResponse(jwtUtil.generateToken(getTokenInfo(savedUser)));
        userResponse.setPreferSport(signUpRequest.getPreferSport());

        return userResponse;
    }

    public UserResponse signIn(SignInRequest signInRequest) {

        final User user = userRepository.getUserByMail(signInRequest.getMail()).orElseThrow(NonExistMailUserException::new);

        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) throw new InvalidPasswordException();

        UserResponse userResponse = UserMapper.INSTANCE.eToRes(user);
        userResponse.setTokenResponse(jwtUtil.generateToken(getTokenInfo(user)));

        List<Prefer> prefers = new ArrayList<>();
        for (PreferSport preferSport : user.getPreferSports())
            prefers.add(preferSport.getPrefer());
        userResponse.setPreferSport(prefers);

        return userResponse;
    }

    private TokenInfo getTokenInfo(User user) { // 이거 JwtUtil로 돌릴지
        return new TokenInfo(user.getId(), user.getMail(), user.getAuthority());
    }

}
