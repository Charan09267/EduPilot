package net.edupilot.aiservice.service;

import net.edupilot.aiservice.dto.InterviewEvaluationRequest;
import net.edupilot.aiservice.prompt.InterviewEvaluationPromptBuilder;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

@Service
public class InterviewEvaluationService {

    private final ChatModel chatModel;
    private final InterviewEvaluationPromptBuilder promptBuilder;

    public InterviewEvaluationService(
            ChatModel chatModel,
            InterviewEvaluationPromptBuilder promptBuilder) {

        this.chatModel = chatModel;
        this.promptBuilder = promptBuilder;
    }

    public String evaluateInterview(
            InterviewEvaluationRequest request) {

        String prompt = promptBuilder.buildPrompt(request);

        return chatModel.call(prompt);
    }
}
