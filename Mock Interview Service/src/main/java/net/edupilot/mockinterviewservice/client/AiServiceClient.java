package net.edupilot.mockinterviewservice.client;

import net.edupilot.mockinterviewservice.dto.request.IntialQuestionRequest;
import net.edupilot.mockinterviewservice.dto.response.IntialQuestionResponse;

public interface AiServiceClient {

    IntialQuestionResponse generateInitialQuestion(
            IntialQuestionRequest request
    );
}
