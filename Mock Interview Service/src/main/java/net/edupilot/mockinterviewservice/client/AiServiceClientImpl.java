package net.edupilot.mockinterviewservice.client;

import net.edupilot.mockinterviewservice.dto.request.IntialQuestionRequest;
import net.edupilot.mockinterviewservice.dto.response.IntialQuestionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AiServiceClientImpl implements AiServiceClient {

    private final RestClient restClient;
    private final String aiServiceUrl;

    public AiServiceClientImpl(
            RestClient restClient,
            @Value("${services.ai-service.url}") String aiServiceUrl) {

        this.restClient = restClient;
        this.aiServiceUrl = aiServiceUrl;
    }

    @Override
    public IntialQuestionResponse generateInitialQuestion(
            IntialQuestionRequest request) {

        return restClient
                .post()
                .uri(aiServiceUrl + "/ai/interviews/normal/initial-question")
                .body(request)
                .retrieve()
                .body(IntialQuestionResponse.class);
    }
}