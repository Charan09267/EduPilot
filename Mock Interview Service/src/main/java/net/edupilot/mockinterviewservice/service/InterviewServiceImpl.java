package net.edupilot.mockinterviewservice.service;


import net.edupilot.mockinterviewservice.dto.CreateInterviewRequest;
import net.edupilot.mockinterviewservice.dto.InterviewResponse;
import net.edupilot.mockinterviewservice.entity.Interview;
import net.edupilot.mockinterviewservice.enums.InterviewStatus;
import net.edupilot.mockinterviewservice.repository.InterviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;

    public InterviewServiceImpl(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }

    @Override
    public InterviewResponse createInterview(CreateInterviewRequest request) {

        Interview interview = new Interview();

        // Temporary value until JWT integration
        interview.setUserId(1L);

        interview.setType(request.getType());
        interview.setStatus(InterviewStatus.CREATED);

        interview.setTargetRole(request.getTargetRole());
        interview.setExperienceLevel(request.getExperienceLevel());

        interview.setDurationMinutes(request.getDurationMinutes());
        interview.setQuestionLimit(request.getQuestionLimit());

        interview.setProgrammingLanguage(request.getProgrammingLanguage());
        interview.setDifficulty(request.getDifficulty());
        interview.setTopic(request.getTopic());

        interview.setCreatedAt(LocalDateTime.now());

        Interview savedInterview = interviewRepository.save(interview);

        return mapToResponse(savedInterview);
    }

    private InterviewResponse mapToResponse(Interview interview) {

        InterviewResponse response = new InterviewResponse();

        response.setId(interview.getId());
        response.setType(interview.getType());
        response.setStatus(interview.getStatus());

        response.setTargetRole(interview.getTargetRole());
        response.setExperienceLevel(interview.getExperienceLevel());

        response.setDurationMinutes(interview.getDurationMinutes());
        response.setQuestionLimit(interview.getQuestionLimit());

        response.setProgrammingLanguage(interview.getProgrammingLanguage());
        response.setDifficulty(interview.getDifficulty());
        response.setTopic(interview.getTopic());

        response.setCreatedAt(interview.getCreatedAt());

        return response;
    }

    @Override
    public InterviewResponse getInterview(Long interviewId) {

        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() ->
                        new RuntimeException("Interview not found"));

        return mapToResponse(interview);
    }

    @Override
    public InterviewResponse startInterview(Long interviewId) {
        return null;
    }
}
