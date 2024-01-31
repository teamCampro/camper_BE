package com.campro.onboarding.presentation.response;

import com.campro.onboarding.infrastructure.entity.OnboardingEntity;

import java.util.List;

public record OnboardingListResponse(List<OnboardingEntity> list) {
    public static OnboardingListResponse from(List<OnboardingEntity> temp){
        return new OnboardingListResponse(temp);
    }

}
