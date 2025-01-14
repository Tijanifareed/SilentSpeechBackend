package com.titans.SilentSpeech.dtos.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountRequest {
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
    private String prefereredLanguage;
}
