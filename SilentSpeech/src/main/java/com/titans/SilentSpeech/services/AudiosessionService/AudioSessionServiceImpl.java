package com.titans.SilentSpeech.services.AudiosessionService;

import com.titans.SilentSpeech.dtos.request.ConvertAudioRequest;
import com.titans.SilentSpeech.dtos.request.StartAudioSessionRequest;
import com.titans.SilentSpeech.dtos.response.AudioUploadResponse;
import com.titans.SilentSpeech.dtos.response.ConvertAudioResponse;
import com.titans.SilentSpeech.dtos.response.StartAudioSessionResponse;
import com.titans.SilentSpeech.entities.AudioSession;
import com.titans.SilentSpeech.repositories.AudioSessionRepository;
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
    @Autowired
    private AudioSessionRepository audioSessionRepository;


    @Override
    public StartAudioSessionResponse startAudioSession(StartAudioSessionRequest request) throws IOException {
        AudioUploadResponse audioUrl = audioService.uploadAudio(request.getAudio());
        AudioSession audioSession = new AudioSession();
        audioSession.setUserId(request.getUserId());
        audioSession.setAudioUrl(audioUrl.getUrl());
        audioSession.setStatus("Processing");
        audioSession.setStartedAt(LocalDateTime.now());
        audioSession.setAudioFormat(audioUrl.getFormat());
        AudioSession audioSession1 = audioSessionRepository.save(audioSession);
        ConvertAudioRequest request1 = new ConvertAudioRequest();
        request1.setAudioUrl(audioSession.getAudioUrl());
        request1.setSessionId(audioSession1.getId());
        ConvertAudioResponse response = transcriptionService.convertAudioToText(request1);
        audioSession.setTranscriptionId(response.getTranscriptionId());
        StartAudioSessionResponse response1 = new StartAudioSessionResponse();

        return response1;
    }
}
