package com.titans.SilentSpeech.services.transcription;

import com.titans.SilentSpeech.dtos.request.ConvertAudioRequest;
import com.titans.SilentSpeech.dtos.response.ConvertAudioResponse;

public interface TranscriptionService {
        ConvertAudioResponse convertAudioToText(ConvertAudioRequest request);
}
