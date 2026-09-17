package net.edupilot.mockinterviewservice.service.interfaces;

import net.edupilot.mockinterviewservice.dto.request.CreateInterviewRequest;
import net.edupilot.mockinterviewservice.dto.response.InterviewResponse;
import net.edupilot.mockinterviewservice.dto.response.InterviewSummaryResponse;
import net.edupilot.mockinterviewservice.dto.response.StartInterviewResponse;

import java.util.List;

public interface InterviewService {
    InterviewResponse createInterview(CreateInterviewRequest request);

    InterviewResponse getInterview(Long interviewId);

    StartInterviewResponse startInterview(Long interviewId);

    List<InterviewSummaryResponse> getMyInterviews();
}
