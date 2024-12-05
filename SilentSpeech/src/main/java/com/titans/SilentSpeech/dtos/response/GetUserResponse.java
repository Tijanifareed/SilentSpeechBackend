package com.titans.SilentSpeech.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserResponse {
    private long userId;
    private String email;
    private String name;
    private String password;
    private String phoneNumber;
    private String prefereredLanguage;
}
