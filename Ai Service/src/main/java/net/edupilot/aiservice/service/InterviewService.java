package net.edupilot.aiservice.service;

import net.edupilot.aiservice.dto.InitialQuestionRequest;
import net.edupilot.aiservice.dto.InitialQuestionResponse;
import net.edupilot.aiservice.dto.NextQuestionRequest;
import net.edupilot.aiservice.dto.NextQuestionResponse;

public interface InterviewService {
    public InitialQuestionResponse generateInitialQuestion(
            InitialQuestionRequest request);

    public NextQuestionResponse generateNextQuestion(
            NextQuestionRequest request);
}
