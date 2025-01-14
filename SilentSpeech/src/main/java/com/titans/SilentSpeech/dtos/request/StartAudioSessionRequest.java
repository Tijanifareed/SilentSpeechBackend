package com.titans.SilentSpeech.dtos.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@ToString
public class StartAudioSessionRequest {
    private Long userId;
    private MultipartFile audioFile;

}
