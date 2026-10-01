package net.edupilot.aiservice.controller;

import net.edupilot.aiservice.dto.InterviewEvaluationRequest;
import net.edupilot.aiservice.service.InterviewEvaluationService;
import net.edupilot.aiservice.service.OllamaService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final InterviewEvaluationService evaluationService;

    public AiController(
            InterviewEvaluationService evaluationService) {

        this.evaluationService = evaluationService;
    }


    @PostMapping("/interviews/evaluate")
    public String evaluateInterview(
            @RequestBody InterviewEvaluationRequest request) {

        return evaluationService.evaluateInterview(request);
    }
}
