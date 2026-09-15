package net.edupilot.mockinterviewservice.service.interfaces;

import net.edupilot.mockinterviewservice.dto.CreateInterviewRequest;
import net.edupilot.mockinterviewservice.dto.InterviewResponse;

public interface InterviewService {
    InterviewResponse createInterview(CreateInterviewRequest request);

    InterviewResponse getInterview(Long interviewId);

    InterviewResponse startInterview(Long interviewId);
}
