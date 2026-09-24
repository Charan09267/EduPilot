package net.edupilot.mockinterviewservice.dto.ai;

import lombok.Data;
import net.edupilot.mockinterviewservice.dto.redis.ConversationTurn;

import java.util.List;

@Data
public class EvaluationRequest {

    private Long interviewId;

    private String targetRole;
    private String experienceLevel;
    private String interviewInstructions;

    private Integer questionLimit;
    private Integer durationMinutes;

    private List<ConversationTurn> conversationHistory;
}
