package com.campro.onboarding.application;

import com.campro.onboarding.infrastructure.entity.OnboardingEntity;
import com.campro.onboarding.infrastructure.repository.OnboardingListRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OnboardingService {


    private final OnboardingListRepository onboardingListRepository;


    public OnboardingService(OnboardingListRepository onboardingListRepository) {
        this.onboardingListRepository = onboardingListRepository;
    }


    public List<OnboardingEntity> getOnboardingList(){
        return onboardingListRepository.findAll();

    }
}
