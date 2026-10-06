package net.edupilot.aiservice.controller;

import lombok.RequiredArgsConstructor;
import net.edupilot.aiservice.dto.InitialQuestionRequest;
import net.edupilot.aiservice.dto.InitialQuestionResponse;
import net.edupilot.aiservice.service.InitialQuestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai/interviews/normal")
@RequiredArgsConstructor
public class NormalInterviewAiController {

    private final InitialQuestionService initialQuestionService;

    @PostMapping("/initial-question")
    public InitialQuestionResponse generateInitialQuestion(
            @RequestBody InitialQuestionRequest request) {

        return initialQuestionService.generateInitialQuestion(request);
    }
}