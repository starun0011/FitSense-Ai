package com.project.fitsense.controller;

import com.project.fitsense.dto.request.ActivityRequest;
import com.project.fitsense.dto.response.ActivityResponse;
import com.project.fitsense.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/activity")
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> createActivity(@RequestBody ActivityRequest request) {
        return ResponseEntity.ok(activityService.createActivity(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<List<ActivityResponse>> getAllActivitiesByUserId(@PathVariable UUID id) {
        return ResponseEntity.ok(activityService.getAllActivitiesByUserId(id));
    }
}
