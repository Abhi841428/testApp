package com.test.Controller;

import com.google.api.gax.rpc.ApiException;
import com.google.cloud.dialogflow.v2.*;
import com.google.protobuf.ByteString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController
public class ChatbotController {
   private String sessionId = generateSessionId();
    @Value("${dialogflow.projectId}") private String projectId;

    @Value("${dialogflow.languageCode}") private String languageCode;
    @PostMapping("/chatbot")
    public String handleUserMessage(@RequestBody String userMessage) {
        try {
            String botResponse = detectIntentTexts(projectId, userMessage, sessionId, languageCode);
            return botResponse;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error processing message";
        }
    }

    private String detectIntentTexts(
            String projectId, String texts, String sessionId, String languageCode) throws IOException, ApiException {
        try (SessionsClient sessionsClient = SessionsClient.create()) {
            SessionName session = SessionName.of(projectId, sessionId);
            TextInput.Builder textInput = TextInput.newBuilder().setText(texts).setLanguageCode(languageCode);
            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();
            DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
            return response.getQueryResult().getFulfillmentText();
        }
    }

    public static String generateSessionId() {
        return UUID.randomUUID().toString();
    }
}

