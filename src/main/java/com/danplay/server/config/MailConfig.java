package com.danplay.server.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter @Setter
@ConfigurationProperties()
@Configuration
public class MailConfig {

    private String name;
    private String link;
    private Long validTime;

}
