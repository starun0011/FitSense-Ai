package com.project.fitsense.service;

import com.project.fitsense.dto.request.ActivityRequest;
import com.project.fitsense.dto.response.ActivityResponse;
import com.project.fitsense.entity.Activity;
import com.project.fitsense.entity.User;
import com.project.fitsense.enums.ActivityType;
import com.project.fitsense.enums.EffortLevel;
import com.project.fitsense.exception.NotFoundException;
import com.project.fitsense.repository.ActivityRepository;
import com.project.fitsense.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public ActivityResponse createActivity(ActivityRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("USER NOT FOUND WITH ID: " + request.getUserId()));
        Activity activity = Activity.builder()
                .user(user)
                .type(request.getType())
                .startedAt(request.getStartedAt())
                .caloriesBurned(calculateBurnedCalories(request.getType(), user.getWeight(), request.getDuration(),
                        request.getEffortLevel()))
                .duration(request.getDuration())
                .effortLevel(request.getEffortLevel())
                // .additionalMatrics(request.getAdditionalMatrics())
                .build();
        Activity savedActivity = activityRepository.save(activity);
        return activityDtoMapper(savedActivity);
    }

    private double calculateBurnedCalories(ActivityType type, int weight, int duration, EffortLevel effortLevel) {
        // total kcal = MET x Weight(in Kg) x Duration(in Hrs) x EffortLevelMultiplier
        // Age, Gender will be added in the future.
        return type.getMet() * weight * (duration / 60.0) * effortLevel.getMultiplier();
    }

    public List<ActivityResponse> getAllActivitiesByUserId(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("USER NOT FOUND WITH ID: " + id));
        List<Activity> activities = activityRepository.findAllByUser_Id(id);
        return activities.stream().map(this::activityDtoMapper).toList();
    }

    public ActivityResponse activityDtoMapper(Activity savedActivity) {
        return ActivityResponse.builder()
                .activityId(savedActivity.getId())
                .userId(savedActivity.getUser().getId())
                .duration(savedActivity.getDuration())
                .type(savedActivity.getType())
                .effortLevel(savedActivity.getEffortLevel())
                .startedAt(savedActivity.getStartedAt())
                .createdAt(savedActivity.getCreatedAt())
                .caloriesBurned(savedActivity.getCaloriesBurned())
                .build();
    }

}
