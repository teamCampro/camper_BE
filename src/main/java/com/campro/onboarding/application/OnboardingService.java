package com.campro.onboarding.application;

import com.campro.onboarding.application.DTO.OnboardingListDTO;
import com.campro.onboarding.infrastructure.entity.OnboardingEntity;
import com.campro.onboarding.infrastructure.repository.OnboardingListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OnboardingService {


    private final OnboardingListRepository onboardingListRepository;


    public OnboardingService(OnboardingListRepository onboardingListRepository) {
        this.onboardingListRepository = onboardingListRepository;
    }


    public List<OnboardingListDTO> getOnboardingList(){
        List<OnboardingEntity> entity = onboardingListRepository.findAll();
        List<OnboardingListDTO> onboardingListDTOS = new ArrayList<>();
        for (OnboardingEntity onboardingEntity : entity) {
            onboardingListDTOS.add(OnboardingListDTO.toDTO(onboardingEntity));
        }
        return onboardingListDTOS;

    }
}
