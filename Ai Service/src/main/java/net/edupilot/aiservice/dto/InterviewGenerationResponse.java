package net.edupilot.aiservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class InterviewGenerationResponse {

    private List<InterviewQuestion> questions;
}
