package com.titans.SilentSpeech.services.transcription;

import com.assemblyai.api.AssemblyAI;
import com.assemblyai.api.resources.transcripts.types.Transcript;
import com.assemblyai.api.resources.transcripts.types.TranscriptOptionalParams;
import com.titans.SilentSpeech.dtos.request.ConvertAudioRequest;
import com.titans.SilentSpeech.dtos.response.ConvertAudioResponse;
import com.titans.SilentSpeech.entities.Transcription;
import com.titans.SilentSpeech.repositories.TranscriptionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class TranscriptionServiceImpl implements TranscriptionService{
    @Value("${assemblyai.api_key}")
    private String apiKey;

    private final TranscriptionRepository transcriptionRepository;

    public TranscriptionServiceImpl(TranscriptionRepository transcriptionRepository) {
        this.transcriptionRepository = transcriptionRepository;
    }

    @Override
    public ConvertAudioResponse convertAudioToText(ConvertAudioRequest request) {
        AtomicReference<String> transcriptedText = new AtomicReference<>("");
            AssemblyAI assemblyAI = AssemblyAI.builder()
                    .apiKey(apiKey)
                    .build();
            String url = request.getAudioUrl();
            var config = TranscriptOptionalParams.builder()
                    .speakerLabels(true)
                    .build();

        try {
            Transcript transcript = assemblyAI.transcripts().transcribe(url, config);
            transcript.getUtterances().ifPresent(utterances -> {
                utterances.forEach(utterance -> {
                    transcriptedText.updateAndGet(text ->
                            text + "Speaker " + utterance.getSpeaker() + ": " + utterance.getText() + "\n");
                });
            });

            Transcription transcription = new Transcription();
            transcription.setText(transcriptedText.get());
            transcription.setStatus("Created");
            transcription.setCreatedAt(LocalDateTime.now());
            transcription.setSessionId(request.getSessionId());
            Transcription transcription1 = transcriptionRepository.save(transcription);
            ConvertAudioResponse response = new ConvertAudioResponse();
            response.setStatus("success");
            response.setTranscriptText(transcriptedText.get());
            response.setTranscriptionId(transcription1.getId());
            return response;
        }catch(Exception e){
            throw new RuntimeException("Error transcribing voice");
        }


    }
}
