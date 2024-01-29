package com.campro.onboarding.presentation.controller;


import com.campro.common.controller.ResponseCode;
import com.campro.common.controller.response.ApiResponse;
import com.campro.onboarding.application.OnboardingService;
import com.campro.onboarding.infrastructure.entity.OnboardingEntity;
import com.campro.onboarding.presentation.response.OnboardingListResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {


    private final OnboardingService onboardingService;

    public OnboardingController(OnboardingService onboardingService) {
        this.onboardingService = onboardingService;
    }


    @Operation(summary = "온보딩 리스트", description = "온보딩 리스트 불러오기")
    @GetMapping
    public ResponseEntity<ApiResponse<List<OnboardingEntity>>> test(){

        List<OnboardingEntity> onboardingList = onboardingService.getOnboardingList();
        return ResponseEntity.ok().body(ApiResponse.from(ResponseCode.GET_ONBOARDINGLIST_SUCCESS.getCode(),ResponseCode.GET_ONBOARDINGLIST_SUCCESS.getMessage(),onboardingList));
    }

}
