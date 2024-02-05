package com.campro.onboarding.presentation.response;

import com.campro.onboarding.application.DTO.OnboardingListDTO;

import java.util.List;

public record OnboardingListResponse(List<OnboardingListDTO> list) {
    public static OnboardingListResponse from(List<OnboardingListDTO> OnboardingList){
        return new OnboardingListResponse(OnboardingList);
    }

}
