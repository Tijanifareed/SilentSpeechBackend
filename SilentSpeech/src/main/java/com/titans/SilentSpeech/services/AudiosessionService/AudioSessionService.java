package com.titans.SilentSpeech.services.AudiosessionService;

import com.titans.SilentSpeech.dtos.request.StartAudioSessionRequest;
import com.titans.SilentSpeech.dtos.response.StartAudioSessionResponse;

import java.io.IOException;

public interface AudioSessionService {
     StartAudioSessionResponse startAudioSession(StartAudioSessionRequest request) throws IOException;


}
