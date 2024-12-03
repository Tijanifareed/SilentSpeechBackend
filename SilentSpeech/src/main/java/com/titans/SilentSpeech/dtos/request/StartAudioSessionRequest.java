package com.titans.SilentSpeech.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class StartAudioSessionRequest {
    private Long userId;
    private MultipartFile audio;

}
