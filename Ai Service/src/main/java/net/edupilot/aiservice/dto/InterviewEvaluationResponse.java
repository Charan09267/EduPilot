package net.edupilot.aiservice.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class InterviewEvaluationResponse {

    private double overallScore;

    private String overallFeedback;

    private List<String> strengths;

    private List<String> weaknesses;

    private List<String> suggestions;
}