package net.edupilot.aiservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.edupilot.aiservice.dto.request.InitialQuestionRequest;
import net.edupilot.aiservice.dto.response.InitialQuestionResponse;
import net.edupilot.aiservice.dto.request.NextQuestionRequest;
import net.edupilot.aiservice.dto.response.NextQuestionResponse;
import net.edupilot.aiservice.service.InterviewServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai/interviews/normal")
@RequiredArgsConstructor
public class NormalInterviewAiController {

    private final InterviewServiceImpl interviewService;

    @PostMapping("/initial-question")
    public InitialQuestionResponse generateInitialQuestion(
            @RequestBody InitialQuestionRequest request) {

        return interviewService.generateInitialQuestion(request);
    }


    @PostMapping("/next-question")
    public NextQuestionResponse generateNextQuestion(
            @Valid @RequestBody NextQuestionRequest request) {

        return interviewService.generateNextQuestion(request);

    }

}