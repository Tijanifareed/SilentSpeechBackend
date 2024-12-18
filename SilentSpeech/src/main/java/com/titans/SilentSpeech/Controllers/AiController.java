package com.titans.SilentSpeech.Controllers;


import com.titans.SilentSpeech.dtos.request.ChatGptRequest;
import com.titans.SilentSpeech.dtos.response.ApiResponse;
import com.titans.SilentSpeech.dtos.response.AudioResponse;
import com.titans.SilentSpeech.dtos.response.ChatGptResponse;
import com.titans.SilentSpeech.services.chatservice.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@CrossOrigin(origins = "*")
public class AiController {
    @Autowired
    private  ChatService chatService;

    public AiController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/summararize")
    public ResponseEntity<?> summarizeMessage(@RequestBody ChatGptRequest request){
        try{
            System.out.println(request.toString());
            ChatGptResponse response = chatService.summarizeMessage(request);
            return new ResponseEntity<>(new ApiResponse(true, response), CREATED);
        }catch (IOException | InterruptedException e){
            return ResponseEntity.status(500).body(new AudioResponse("Audio upload failed"));
        }catch (RuntimeException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

}
