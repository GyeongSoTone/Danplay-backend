package com.danplay.server.user.presentation;

import com.danplay.server.user.application.UserService;
import com.danplay.server.user.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user/")
public class UserController {

    private final UserService userService;

    @PostMapping("signup")
    public ResponseEntity<UserResponse> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        return ResponseEntity.ok().body(userService.signUp(signUpRequest));
    }

    @PostMapping("signin")
    public ResponseEntity<UserResponse> signIn(@RequestBody @Valid SignInRequest signInRequest) {
        return ResponseEntity.ok().body(userService.signIn(signInRequest));
    }

    @PutMapping("find")
    public ResponseEntity<UserResponse> findPassword(@RequestBody @Valid FindPasswordRequest findPasswordRequest) {
        return ResponseEntity.ok().body(userService.findPassword(findPasswordRequest));
    }

//    @PutMapping("reset")
//    public ResponseEntity<UserResponse> resetPassword(@RequestBody @Valid ResetPasswordRequest resetPasswordRequest) {
//        return ResponseEntity.ok().body(userService.resetPassword(resetPasswordRequest));
//    }
//
//    @PutMapping("reset/prefer")
//    public ResponseEntity<UserResponse> resetPassword(@RequestBody @Valid ResetPasswordRequest resetPasswordRequest) {
//        return ResponseEntity.ok().body(userService.resetPassword(resetPasswordRequest));
//    }
}
