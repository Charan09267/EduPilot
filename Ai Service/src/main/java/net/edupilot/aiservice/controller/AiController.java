package net.edupilot.aiservice.controller;

import jakarta.validation.Valid;
import net.edupilot.aiservice.dto.InterviewEvaluationRequest;
import net.edupilot.aiservice.dto.InterviewEvaluationResponse;
import net.edupilot.aiservice.dto.InterviewGenerationRequest;
import net.edupilot.aiservice.dto.InterviewGenerationResponse;
import net.edupilot.aiservice.service.InterviewEvaluationService;
import net.edupilot.aiservice.service.InterviewServiceImpl;
import net.edupilot.aiservice.service.InterviewServiceImpl;
import net.edupilot.aiservice.service.OllamaService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AiController {

    private final InterviewEvaluationService evaluationService;
    private final InterviewServiceImpl generationService;

    public AiController(
            InterviewEvaluationService evaluationService,
            InterviewServiceImpl generationService) {

        this.evaluationService = evaluationService;
        this.generationService = generationService;
    }

    @PostMapping("/interviews/evaluate")
    public InterviewEvaluationResponse evaluateInterview(
            @Valid @RequestBody InterviewEvaluationRequest request) {

        return evaluationService.evaluateInterview(request);
    }

//    @PostMapping("/interviews/generate")
//    public InterviewGenerationResponse generateQuestions(
//            @Valid @RequestBody InterviewGenerationRequest request) {
//
//        return generationService.generateQuestions(request);
//    }
}
