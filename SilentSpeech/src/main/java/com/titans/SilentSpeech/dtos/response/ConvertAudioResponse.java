package com.titans.SilentSpeech.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConvertAudioResponse {
    private String transcriptText;
    private Long transcriptionId;
    private String status;

}

