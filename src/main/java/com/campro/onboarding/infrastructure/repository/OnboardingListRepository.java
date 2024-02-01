package com.campro.onboarding.infrastructure.repository;

import com.campro.onboarding.infrastructure.entity.OnboardingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface OnboardingListRepository extends JpaRepository<OnboardingEntity, Long> {

    List<OnboardingEntity> findAll();

}
