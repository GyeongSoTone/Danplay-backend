package com.danplay.server.mail.presentation;

import com.danplay.server.mail.application.MailService;
import com.danplay.server.mail.dto.MailBooleanResponse;
import com.danplay.server.mail.dto.MailCodeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/mail/")
public class MailController {

    private final MailService mailService;

    @PostMapping("mailconfirm/{mail}")
    public ResponseEntity<MailBooleanResponse> mailConfirm(@PathVariable("mail") String mail) {
        return ResponseEntity.ok().body(mailService.sendVerificationMail(mail));
    }

    @PostMapping("mailcodeconfirm/")
    public ResponseEntity<MailBooleanResponse> mailCodeConfirm(@RequestBody MailCodeRequest mailCodeRequest) {
        return ResponseEntity.ok().body(mailService.checkMailCode(mailCodeRequest));
    }
}
