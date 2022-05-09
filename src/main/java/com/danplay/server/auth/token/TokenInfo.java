package com.danplay.server.auth.token;

import com.danplay.server.auth.enumerations.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenInfo {

    private Long id;

    private String email;

    private Authority authority;
}
