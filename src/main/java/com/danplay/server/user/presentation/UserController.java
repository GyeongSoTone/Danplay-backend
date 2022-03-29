package com.danplay.server.user.presentation;

import com.danplay.server.mail.application.MailService;
import com.danplay.server.mail.dto.MailBooleanResponse;
import com.danplay.server.user.application.UserService;
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
    private final MailService mailService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok().body(userService.signUp(signUpRequest));
    }

    @PostMapping("mailconfirm/{mail}")
    public ResponseEntity<MailBooleanResponse> mailConfirm(@PathVariable("mail") String mail) {
        return ResponseEntity.ok().body(mailService.sendVerificationMail(mail));
    }
}
