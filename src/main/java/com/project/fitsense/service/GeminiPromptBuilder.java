package com.project.fitsense.service;

import com.project.fitsense.enums.ActivityType;
import com.project.fitsense.enums.EffortLevel;


public class GeminiPromptBuilder {

    public static String buildFitnessPrompt(
            double weight,
            ActivityType activityType,
            EffortLevel effortLevel,
            double caloriesBurned,
            int durationMinutes
    ) {

        return """
                You are a fitness recommendation engine.
                
                User Activity Data:
                - Body Weight: %.1f kg
                - Activity Type: %s
                - Activity Intensity (MET): %.1f
                - Effort Level: %s
                - Effort Multiplier: %.1f
                - Duration: %d minutes
                - Calories Burned: %.1f kcal
                
                Interpretation Rules:
                - MET represents baseline exercise intensity
                - Effort multiplier reflects how hard the user pushed
                - Higher MET + higher effort = higher physical stress
                
                Task:
                Analyze the activity and generate a personalized fitness recommendation.
                
                RESPONSE RULES (MANDATORY):
                1. Respond ONLY in valid JSON
                2. No markdown, no explanation text
                3. JSON structure MUST be exactly:
                
                {
                  "recommendation": "string",
                  "improvements": ["string"],
                  "safety": ["string"],
                  "suggestions": ["string"]
                }
                
                Content Guidelines:
                - recommendation: 2–3 sentence summary of performance
                - improvements: training or efficiency improvements
                - safety: recovery and injury-prevention advice
                - suggestions: next workout, rest, hydration, nutrition
                """.formatted(
                weight,
                activityType.name(),
                activityType.getMet(),
                effortLevel.name(),
                effortLevel.getMultiplier(),
                durationMinutes,
                caloriesBurned
        );
    }
}

