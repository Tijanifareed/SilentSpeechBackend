package com.titans.SilentSpeech.dtos.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartAudioSessionResponse {
    public Long sessionId;
    public String audioUrl;
    public int transcriptionId;
    public String transcriptionText;
    public String status;
    public String message;
}
