package net.edupilot.mockinterviewservice.controller;

import jakarta.validation.Valid;
import net.edupilot.mockinterviewservice.dto.CreateInterviewRequest;
import net.edupilot.mockinterviewservice.dto.InterviewResponse;
import net.edupilot.mockinterviewservice.service.interfaces.InterviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping
    public ResponseEntity<InterviewResponse> createInterview(
            @Valid @RequestBody CreateInterviewRequest request) {

        InterviewResponse response = interviewService.createInterview(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{interviewId}")
    public ResponseEntity<InterviewResponse> getInterview(
            @PathVariable Long interviewId) {

        return ResponseEntity.ok(
                interviewService.getInterview(interviewId)
        );
    }

    @PostMapping("/{interviewId}/start")
    public ResponseEntity<InterviewResponse> startInterview(
            @PathVariable Long interviewId) {

        return ResponseEntity.ok(
                interviewService.startInterview(interviewId)
        );
    }
}
