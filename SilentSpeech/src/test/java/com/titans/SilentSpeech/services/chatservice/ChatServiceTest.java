package com.titans.SilentSpeech.services.chatservice;

import com.titans.SilentSpeech.dtos.request.ChatGptRequest;
import com.titans.SilentSpeech.dtos.response.ChatGptResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ChatServiceTest {

    @Test
    public void test() throws IOException, InterruptedException {
        ChatService chatService = new ChatService();
        ChatGptRequest request = new ChatGptRequest();
        request.setContent("Message 1: Testing, testing.\\nMessage 2: Guy, I fuck bitches.\\nMessage 3: Guys.\\nMessage 4: Fuck you now.\\nMessage 5: Guys.\\nMessage 6: I love guys.\\nMessage 7: Guy.\\nMessage 8: Shut up.\\nMessage 9: I love guy I don't love.\\n\\n");
        ChatGptResponse response = chatService.summarizeMessage(request);
        assertThat(response).isEqualTo("");
    }


}