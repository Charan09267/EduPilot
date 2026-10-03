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
                
                Evaluate the candidate based on the information above.

                Provide:
                - Overall score from 0 to 10
                - Overall feedback
                - Key strengths
                - Key weaknesses
                - Specific suggestions for improvement

                Be concise, objective and technically accurate.
                """);

        return prompt.toString();
    }
}
