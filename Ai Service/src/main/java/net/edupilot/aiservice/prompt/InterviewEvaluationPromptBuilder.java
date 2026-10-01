package net.edupilot.aiservice.prompt;

import net.edupilot.aiservice.dto.InterviewEvaluationRequest;
import net.edupilot.aiservice.dto.InterviewQuestionAnswer;
import org.springframework.stereotype.Component;

@Component
public class InterviewEvaluationPromptBuilder {

    public String buildPrompt(InterviewEvaluationRequest request) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("""
                You are an experienced technical interviewer.

                Evaluate the candidate's interview performance objectively.

                Consider:
                - Technical correctness
                - Understanding of concepts
                - Clarity of explanation
                - Completeness of answers
                - Relevance of answers

                Interview Details:
                Interview Type: %s
                Role: %s
                Topic: %s

                Questions and Answers:
                """.formatted(
                request.getInterviewType(),
                request.getRole(),
                request.getTopic()
        ));

        int questionNumber = 1;

        for (InterviewQuestionAnswer qa : request.getQuestions()) {

            prompt.append("\nQuestion ")
                    .append(questionNumber)
                    .append(": ")
                    .append(qa.getQuestion());

            prompt.append("\nCandidate Answer: ")
                    .append(qa.getAnswer());

            questionNumber++;
        }

        prompt.append("""

                Evaluate the complete interview.

                Return the evaluation in the following JSON format:

                {
                  "overallScore": number,
                  "overallFeedback": "string",
                  "strengths": ["string"],
                  "weaknesses": ["string"],
                  "suggestions": ["string"]
                }

                The overallScore must be between 0 and 10.

                Return ONLY valid JSON.
                Do not include markdown.
                Do not include ```json.
                """);

        return prompt.toString();
    }
}
