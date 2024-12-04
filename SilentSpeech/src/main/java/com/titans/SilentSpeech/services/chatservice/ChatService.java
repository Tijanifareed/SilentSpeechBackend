package com.titans.SilentSpeech.services.chatservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.titans.SilentSpeech.dtos.response.ChatGptResponse;
import com.titans.SilentSpeech.dtos.request.ChatGptRequest;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Configuration
@Slf4j
public class ChatService {

    @Value("${gemini.api_key}")
    private  String aiApiKey;





    public  ChatGptResponse summarizeMessage(ChatGptRequest request) throws IOException, InterruptedException {

        // Prepare the request body with the input text
        System.out.println("request: "+request.getContent());
        String body = """
            {
                "contents": [
                    {
                        "parts": [
                            {
                                "text": "%s"
                            }
                        ]
                    }
                ]
            }
            """.formatted(request.getContent()+"summarize this like what is going on in this conversation");

        // API key and endpoint

        String apiKey = aiApiKey;
        String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + apiKey;

        // Create the HTTP request
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        // Send the request
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // Parse and return the extracted text
        String text = parseTextFromResponse(response.body());
        ChatGptResponse response1 = new ChatGptResponse();
        response1.setContent(text);
        return response1;
    }


    private static String parseTextFromResponse(String responseBody) {
        try {
            // Parse the JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode textNode = rootNode.path("candidates").get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text");

            // Return the text content if it exists, or an error message if not
            return textNode.isMissingNode() ? "Text not found in response." : textNode.asText();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error parsing response.";
        }
    }


//







}
