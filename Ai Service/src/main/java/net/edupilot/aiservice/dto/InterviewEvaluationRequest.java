package net.edupilot.aiservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class InterviewEvaluationRequest {

    private String interviewType;

    private String role;

    private String topic;

    @Valid
    private List<InterviewQuestionAnswer> questions;
}
