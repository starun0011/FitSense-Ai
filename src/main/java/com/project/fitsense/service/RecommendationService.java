package com.project.fitsense.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.fitsense.dto.GeminiRecommendationResponse;
import com.project.fitsense.dto.request.GeminiRequestDto;
import com.project.fitsense.dto.request.RecommendationRequest;
import com.project.fitsense.dto.response.GeminiResponseDto;
import com.project.fitsense.dto.response.RecommendationResponse;
import com.project.fitsense.entity.Activity;
import com.project.fitsense.entity.Recommendation;
import com.project.fitsense.entity.User;
import com.project.fitsense.exception.NotFoundException;
import com.project.fitsense.repository.ActivityRepository;
import com.project.fitsense.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RestTemplate restTemplate;
    private final ActivityRepository activityRepository;
    private final ObjectMapper objectMapper;
    private final RecommendationRepository recommendationRepository;

    @Value("${gemini.api.key}")
    private String api;

    @Value("${gemini.api.url}")
    private String url;

    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-goog-api-key", api);
        return headers;
    }

    public RecommendationResponse getRecommendation(RecommendationRequest request) {
        Activity activity = activityRepository.findById(request.getActivityId())
                .orElseThrow(() -> new NotFoundException("ACTIVITY NOT FOUND WITH ID: " + request.getActivityId()));
        User user = activity.getUser();
        String prompt = GeminiPromptBuilder.buildFitnessPrompt(
                user.getWeight(),
                activity.getType(),
                activity.getEffortLevel(),
                activity.getCaloriesBurned(),
                activity.getDuration()
        );
        GeminiRequestDto geminiRequest = GeminiRequestDto.getGeminiRequest(prompt);
        HttpEntity<GeminiRequestDto> entity = new HttpEntity<>(geminiRequest, getHeaders());
        ResponseEntity<GeminiResponseDto> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                GeminiResponseDto.class
        );
        String geminiText = response.getBody()
                .getCandidates()
                .get(0)
                .getContent()
                .getParts()
                .get(0)
                .getText();

        System.out.println(geminiText);
        String extractJsonText = extractJson(geminiText);
        System.out.println(extractJsonText);

        GeminiRecommendationResponse finalResponse;
        try {
            finalResponse = objectMapper.readValue(
                    extractJsonText,
                    GeminiRecommendationResponse.class
            );
        } catch (Exception e) {
            throw new RuntimeException("Invalid Gemini JSON response", e);
        }

        Recommendation recommendation = new Recommendation();
        recommendation.setRecommendation(finalResponse.getRecommendation());
        recommendation.setSafety(finalResponse.getSafety());
        recommendation.setImprovements(finalResponse.getImprovements());
        recommendation.setSuggestions(finalResponse.getSuggestions());

        recommendation.setUser(user);
        recommendation.setActivity(activity);
        Recommendation savedRecommendation = recommendationRepository.save(recommendation);


        return recommendationDtoMapper(savedRecommendation);
    }

    private RecommendationResponse recommendationDtoMapper(Recommendation recommendation) {
        return RecommendationResponse.builder()
                .id(recommendation.getId())
                .activityId(recommendation.getActivity().getId())
                .userId(recommendation.getUser().getId())
                .recommendation(recommendation.getRecommendation())
                .improvements(recommendation.getImprovements())
                .suggestions(recommendation.getSuggestions())
                .safety(recommendation.getSafety())
                .createdAt(recommendation.getCreatedAt())
                .updatedAt(recommendation.getUpdatedAt())
                .build();
    }

    private String extractJson(String text) {

        // Remove markdown fences
        String cleaned = text
                .replaceAll("```json", "")
                .replaceAll("```", "")
                .trim();

        // Extract JSON block safely
        int start = cleaned.indexOf('{');
        int end = cleaned.lastIndexOf('}');

        if (start == -1 || end == -1 || start > end) {
            throw new IllegalArgumentException("No valid JSON object found");
        }

        return cleaned.substring(start, end + 1);
    }

}
