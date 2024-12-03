package com.titans.SilentSpeech.services.transcription;

import com.assemblyai.api.AssemblyAI;
import com.assemblyai.api.resources.transcripts.types.Transcript;
import com.assemblyai.api.resources.transcripts.types.TranscriptOptionalParams;
import com.titans.SilentSpeech.dtos.request.ConvertAudioRequest;
import com.titans.SilentSpeech.dtos.response.ConvertAudioResponse;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;


@Service
public class TranscriptionServiceImpl implements TranscriptionService{

    @Override
    public ConvertAudioResponse convertAudioToText(ConvertAudioRequest request) {
        AtomicReference<String> transcriptedText = new AtomicReference<>("");
            AssemblyAI assemblyAI = AssemblyAI.builder()
                    .apiKey("")
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

            return new ConvertAudioResponse(transcriptedText.get(), "success");
        }catch(Exception e){
            return new ConvertAudioResponse("Error", "failed: " + e.getMessage());

        }


    }
}
