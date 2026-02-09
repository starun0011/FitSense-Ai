package com.project.fitsense.dto.request;

import com.project.fitsense.enums.ActivityType;
import com.project.fitsense.enums.EffortLevel;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@Data
public class ActivityRequest {

    private UUID userId;

    private ActivityType type;

    private int duration;

    private EffortLevel effortLevel;

    private OffsetDateTime startedAt;

}
