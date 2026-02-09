package com.project.fitsense.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.fitsense.entity.User;
import com.project.fitsense.enums.ActivityType;
import com.project.fitsense.enums.EffortLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityResponse {

    private UUID activityId;

    private UUID userId;

    private ActivityType type;

    private OffsetDateTime startedAt;

    private int duration;

    private EffortLevel effortLevel;

    private double caloriesBurned;

    private OffsetDateTime createdAt;
}
