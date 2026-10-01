package net.edupilot.aiservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class InterviewEvaluationRequest {

    private String interviewType;

    private String role;

    private String topic;

    private List<InterviewQuestionAnswer> questions;
}
