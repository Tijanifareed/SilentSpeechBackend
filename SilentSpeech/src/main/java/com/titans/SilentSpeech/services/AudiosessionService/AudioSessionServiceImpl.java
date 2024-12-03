package com.titans.SilentSpeech.services.AudiosessionService;

import com.titans.SilentSpeech.dtos.request.StartAudioSessionRequest;
import com.titans.SilentSpeech.dtos.response.AudioUploadResponse;
import com.titans.SilentSpeech.dtos.response.StartAudioSessionResponse;
import com.titans.SilentSpeech.entities.AudioSession;
import com.titans.SilentSpeech.services.AudioService.AudioService;
import com.titans.SilentSpeech.services.transcription.TranscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;


@Service
public class AudioSessionServiceImpl implements AudioSessionService{

    @Autowired
    private AudioService audioService;

    @Autowired
    private TranscriptionService transcriptionService;


    @Override
    public StartAudioSessionResponse startAudioSession(StartAudioSessionRequest request) throws IOException {
        AudioUploadResponse audioUrl = audioService.uploadAudio(request.getAudio());
        AudioSession audioSession = new AudioSession();
        audioSession.setUserId(request.getUserId());
        audioSession.setAudioUrl(audioUrl.getUrl());
        audioSession.setStatus("Processing");
        audioSession.setStartedAt(LocalDateTime.now());
        audioSession.setAudioFormat(audioUrl.getFormat());

        return null;
    }
}
