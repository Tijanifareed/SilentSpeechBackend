package com.titans.SilentSpeech.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConvertAudioRequest {
    private Long sessionId;
    private String audioUrl;



}
