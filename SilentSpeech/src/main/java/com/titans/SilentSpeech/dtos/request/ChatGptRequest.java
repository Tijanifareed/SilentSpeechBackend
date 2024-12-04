package com.titans.SilentSpeech.dtos.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;

@Getter
@ToString
@Setter

public class ChatGptRequest {
    private String content;
}
