package com.project.fitsense.controller;

import com.project.fitsense.dto.request.RecommendationRequest;
import com.project.fitsense.dto.response.RecommendationResponse;
import com.project.fitsense.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {
    public final RecommendationService recommendationService;

    @PostMapping
    public ResponseEntity<RecommendationResponse> getRecommendation(@RequestBody RecommendationRequest request) {
        return ResponseEntity.ok(recommendationService.getRecommendation(request));
    }
}
