package com.danplay.server.user.presentation;

import com.danplay.server.user.application.UserService;
import com.danplay.server.user.dto.SignInRequest;
import com.danplay.server.user.dto.SignUpRequest;
import com.danplay.server.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user/")
public class UserController {

    private final UserService userService;

    @PostMapping("signup")
    public ResponseEntity<UserResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok().body(userService.signUp(signUpRequest));
    }

    @PostMapping("signin")
    public ResponseEntity<UserResponse> signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok().body(userService.signIn(signInRequest));
    }
}
