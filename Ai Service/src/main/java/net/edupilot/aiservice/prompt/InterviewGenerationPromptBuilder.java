package net.edupilot.aiservice.prompt;

import net.edupilot.aiservice.dto.InterviewGenerationRequest;
import org.springframework.stereotype.Component;

@Component
public class InterviewGenerationPromptBuilder {

    public String buildPrompt(InterviewGenerationRequest request) {

        String customPrompt = request.getCustomPrompt();

        if (customPrompt == null || customPrompt.isBlank()) {
            customPrompt = "No additional instructions provided.";
        }

        return """
                You are an experienced technical interviewer.

                Generate interview questions for the candidate based on the
                following requirements.

                Role:
                %s

                Topics:
                %s

                Number of Questions:
                %d

                Interview Time Limit:
                %d minutes

                Additional Instructions:
                %s

                Requirements:

                1. Generate exactly %d questions.
                2. Questions should match the candidate's role.
                3. Cover the provided topics.
                4. Start with moderate difficulty.
                5. Gradually increase the difficulty.
                6. Avoid duplicate questions.
                7. Questions should test understanding rather than memorization.
                8. Include practical or scenario-based questions where appropriate.
                9. Keep each question clear and concise.
                10. Number the questions sequentially starting from 1.

                IMPORTANT RESPONSE FORMAT:

                Return ONLY a valid JSON object.

                The JSON object MUST contain a field named "questions".

                The "questions" field MUST be an array of objects.

                Each question object MUST contain exactly these two fields:

                - "questionNumber": the sequential question number.
                - "question": the actual interview question.

                The response MUST follow exactly this structure:

                {
                  "questions": [
                    {
                      "questionNumber": 1,
                      "question": "First interview question"
                    },
                    {
                      "questionNumber": 2,
                      "question": "Second interview question"
                    },
                    {
                      "questionNumber": 3,
                      "question": "Third interview question"
                    }
                  ]
                }

                Rules:

                - Generate exactly %d question objects.
                - questionNumber must start from 1.
                - questionNumber must increase sequentially.
                - Each question must be a string.
                - Do not return questions as plain strings.
                - Do not return a JSON array directly.
                - Do not add any fields other than "questions", "questionNumber", and "question".
                - Do not use markdown.
                - Do not include explanations outside the JSON.
                - Return valid JSON only.
                """.formatted(
                request.getRole(),
                request.getTopic(),
                request.getNumberOfQuestions(),
                request.getTimeLimit(),
                customPrompt,
                request.getNumberOfQuestions(),
                request.getNumberOfQuestions()
        );
    }

}