package com.titans.SilentSpeech.dtos.response;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StartAudioSessionResponse {
    public Long sessionId;
    public String audioUrl;
    public String transcriptionText;
    public String status;
    public String message;
}
