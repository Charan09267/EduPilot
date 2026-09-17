package net.edupilot.mockinterviewservice.service;


import net.edupilot.mockinterviewservice.dto.request.CreateInterviewRequest;
import net.edupilot.mockinterviewservice.dto.response.InterviewResponse;
import net.edupilot.mockinterviewservice.dto.response.InterviewSummaryResponse;
import net.edupilot.mockinterviewservice.dto.redis.InterviewContext;
import net.edupilot.mockinterviewservice.dto.response.StartInterviewResponse;
import net.edupilot.mockinterviewservice.entity.Interview;
import net.edupilot.mockinterviewservice.enums.InterviewStatus;
import net.edupilot.mockinterviewservice.repository.InterviewRepository;
import net.edupilot.mockinterviewservice.service.interfaces.InterviewRedisService;
import net.edupilot.mockinterviewservice.service.interfaces.InterviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final InterviewRedisService interviewRedisService;

    public InterviewServiceImpl(
            InterviewRepository interviewRepository,
            InterviewRedisService interviewRedisService) {

        this.interviewRepository = interviewRepository;
        this.interviewRedisService = interviewRedisService;
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
    public StartInterviewResponse startInterview(Long interviewId) {

        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() ->
                        new RuntimeException("Interview not found"));

        if (interview.getStatus() != InterviewStatus.CREATED) {
            throw new RuntimeException(
                    "Interview cannot be started in current state"
            );
        }

        LocalDateTime startedAt = LocalDateTime.now();

        interview.setStatus(InterviewStatus.IN_PROGRESS);
        interview.setStartedAt(startedAt);

        Interview savedInterview = interviewRepository.save(interview);

        String firstQuestion =
                "Can you introduce yourself and explain your technical background?";

        InterviewContext context = new InterviewContext();

        context.setInterviewId(savedInterview.getId());
        context.setUserId(savedInterview.getUserId());
        context.setType(savedInterview.getType());

        context.setTargetRole(savedInterview.getTargetRole());
        context.setExperienceLevel(savedInterview.getExperienceLevel());
        context.setInterviewInstructions(
                savedInterview.getInterviewInstructions()
        );

        context.setDurationMinutes(savedInterview.getDurationMinutes());
        context.setQuestionLimit(savedInterview.getQuestionLimit());

        context.setQuestionsAsked(1);
        context.setCurrentQuestion(firstQuestion);
        context.getQuestions().add(firstQuestion);
        context.setStartedAt(startedAt);

        interviewRedisService.saveContext(context);

        StartInterviewResponse response = new StartInterviewResponse();

        response.setInterviewId(savedInterview.getId());
        response.setStatus(savedInterview.getStatus());
        response.setFirstQuestion(firstQuestion);
        response.setQuestionsAsked(context.getQuestionsAsked());
        response.setQuestionLimit(savedInterview.getQuestionLimit());

        return response;
    }


    @Override
    public List<InterviewSummaryResponse> getMyInterviews() {

        // Temporary user ID until JWT integration
        Long userId = 1L;

        return interviewRepository.findAllByUserId(userId)
                .stream()
                .map(this::mapToSummaryResponse)
                .toList();
    }

    private InterviewSummaryResponse mapToSummaryResponse(
            Interview interview) {

        InterviewSummaryResponse response =
                new InterviewSummaryResponse();

        response.setId(interview.getId());
        response.setType(interview.getType());
        response.setStatus(interview.getStatus());
        response.setTargetRole(interview.getTargetRole());
        response.setDurationMinutes(interview.getDurationMinutes());
        response.setQuestionLimit(interview.getQuestionLimit());
        response.setCreatedAt(interview.getCreatedAt());

        return response;
    }
}
