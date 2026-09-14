package net.edupilot.mockinterviewservice.dto.redis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.edupilot.mockinterviewservice.enums.InterviewType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class InterviewContext implements Serializable {

    private Long interviewId;
    private Long userId;

    private InterviewType type;

    private String targetRole;
    private String experienceLevel;
    private String interviewInstructions;

    private Integer durationMinutes;
    private Integer questionLimit;

    private Integer questionsAsked;

    private String currentQuestion;

    private List<String> questions = new ArrayList<>();
    private List<String> answers = new ArrayList<>();

    private LocalDateTime startedAt;

    // getters and setters
}
