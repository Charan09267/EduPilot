package net.edupilot.aiservice.service;

import lombok.RequiredArgsConstructor;
import net.edupilot.aiservice.dto.InterviewGenerationRequest;
import net.edupilot.aiservice.dto.InterviewGenerationResponse;
import net.edupilot.aiservice.exception.AiServiceException;
import net.edupilot.aiservice.prompt.InterviewGenerationPromptBuilder;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class InterviewGenerationService {

    private final ChatClient chatClient;
    private final InterviewGenerationPromptBuilder promptBuilder;
    private final ObjectMapper objectMapper;

    public InterviewGenerationResponse generateQuestions(
            InterviewGenerationRequest request) throws AiServiceException {

        try {
            String prompt = promptBuilder.buildPrompt(request);

            return chatClient
                    .prompt()
                    .user(prompt)
                    .call()
                    .entity(InterviewGenerationResponse.class);

        } catch (Exception exception) {
            throw new AiServiceException(
                    "Failed to generate interview questions",
                    exception
            );
        }
    }
}
