package net.edupilot.aiservice.service;

import lombok.RequiredArgsConstructor;
import net.edupilot.aiservice.dto.InitialQuestionRequest;
import net.edupilot.aiservice.dto.InitialQuestionResponse;
import net.edupilot.aiservice.exception.AiServiceException;
import net.edupilot.aiservice.prompt.InitialQuestionPromptBuilder;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitialQuestionService {

    private final ChatClient chatClient;
    private final InitialQuestionPromptBuilder promptBuilder;

    public InitialQuestionResponse generateInitialQuestion(
            InitialQuestionRequest request) {

        String prompt = promptBuilder.build(request);

        try {
            return chatClient
                    .prompt()
                    .user(prompt)
                    .call()
                    .entity(InitialQuestionResponse.class);

        } catch (Exception e) {
            throw new AiServiceException(
                    "Failed to generate initial interview question", e);
        }
    }
}
