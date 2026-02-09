package com.project.fitsense.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendationResponse {

    private UUID id;

    private String recommendation;

    private List<String> improvements;

    private List<String> safety;

    private List<String> suggestions;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    private UUID userId;

    private UUID activityId;
}
