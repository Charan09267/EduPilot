package net.edupilot.aiservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class InterviewEvaluationRequest {

    @NotBlank(message = "Interview type is required")
    private String interviewType;

    @NotBlank(message = "Role is required")
    private String role;

    @NotBlank(message = "Topic is required")
    private String topic;

    @NotEmpty(message = "At least one question and answer is required")
    @Valid
    private List<InterviewQuestionAnswer> questions;
}
