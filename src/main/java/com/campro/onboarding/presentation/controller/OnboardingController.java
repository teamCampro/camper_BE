package com.campro.onboarding.presentation.controller;


import com.campro.common.controller.ResponseCode;
import com.campro.common.controller.response.ApiResponse;
import com.campro.onboarding.application.DTO.OnboardingListDTO;
import com.campro.onboarding.application.OnboardingService;
import com.campro.onboarding.presentation.request.OnboardingResultRequest;
import com.campro.onboarding.presentation.response.OnboardingListResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<ApiResponse<OnboardingListResponse>> getOnboardingList(){
        List<OnboardingListDTO> onboardingList = onboardingService.getOnboardingList();
        return ResponseEntity.ok().body(ApiResponse.from(ResponseCode.GET_ONBOARDINGLIST_SUCCESS.getCode(),ResponseCode.GET_ONBOARDINGLIST_SUCCESS.getMessage(),OnboardingListResponse.from(onboardingList)));
    }


    @Operation(summary = "온보딩 결과", description = "온보딩 결과 저장")
    @PostMapping
    public ResponseEntity<ApiResponse< OnboardingResultRequest>> postOnboardingResult(@RequestBody OnboardingResultRequest request){
        return ResponseEntity.ok().body(ApiResponse.from(ResponseCode.GET_ONBOARDINGLIST_SUCCESS.getCode(),ResponseCode.GET_ONBOARDINGLIST_SUCCESS.getMessage(),request));
    }

}
