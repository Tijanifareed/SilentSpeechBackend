package com.titans.SilentSpeech.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpRequest {

    private String email;
    private String name;
    private String password;
    private String phoneNumber;
    private String prefereredLanguage;
}
