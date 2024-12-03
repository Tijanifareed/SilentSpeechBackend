package com.titans.SilentSpeech.Controllers;


import com.titans.SilentSpeech.dtos.request.StartAudioSessionRequest;
import com.titans.SilentSpeech.dtos.response.ApiResponse;
import com.titans.SilentSpeech.dtos.response.AudioResponse;
import com.titans.SilentSpeech.dtos.response.AudioUploadResponse;
import com.titans.SilentSpeech.dtos.response.StartAudioSessionResponse;
import com.titans.SilentSpeech.services.AudiosessionService.AudioSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@CrossOrigin(origins = "*")
public class AudioSessionController {
    @Autowired
    private AudioSessionService audioSessionService;

    @PostMapping("/transcribe-audio")
    public ResponseEntity<?> startAudioSession(@ModelAttribute StartAudioSessionRequest request){
        try{
            StartAudioSessionResponse response = audioSessionService.startAudioSession(request);
            return new ResponseEntity<>(new ApiResponse(true, response),CREATED);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(new AudioResponse("Audio upload failed"));
        }catch (RuntimeException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }

    }

}
