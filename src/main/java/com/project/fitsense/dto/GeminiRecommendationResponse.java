package com.project.fitsense.dto;

import lombok.Data;

import java.util.List;

@Data
public class GeminiRecommendationResponse {
    private String recommendation;
    private List<String> improvements;
    private List<String> safety;
    private List<String> suggestions;
}
