package com.titans.SilentSpeech.dtos.response;

import java.util.List;

public class OpenAiApiResponse {

    private List<Choice> choices;

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public static class Choice {
        private ChatGptResponse message;

        public ChatGptResponse getMessage() {
            return message;
        }

        public void setMessage(ChatGptResponse message) {
            this.message = message;
        }
    }
}
