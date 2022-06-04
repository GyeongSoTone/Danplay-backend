package com.danplay.server.mail.presentation;

import com.danplay.server.mail.application.MailService;
import com.danplay.server.mail.dto.MailCodeRequest;
import com.danplay.server.mail.dto.MailRequest;
import com.danplay.server.mail.dto.MailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/mail/")
public class MailController {

    private final MailService mailService;

    @PostMapping("confirm")
    public ResponseEntity<MailResponse> mailConfirm(@Valid @RequestBody MailRequest mailRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mailService.sendVerificationMail(mailRequest.getMail()));
    }

    @PostMapping("code")
    public ResponseEntity<MailResponse> mailCodeConfirm(@Valid @RequestBody MailCodeRequest mailCodeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mailService.checkMailCode(mailCodeRequest));
    }
}
