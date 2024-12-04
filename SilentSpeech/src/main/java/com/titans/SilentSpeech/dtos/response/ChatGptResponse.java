package com.titans.SilentSpeech.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)

public class ChatGptResponse {
    private String role;
    private String content;
}
