package com.campro.member.presentation.controller;

import com.campro.common.controller.ResponseCode;
import com.campro.common.controller.response.ApiResponse;
import com.campro.member.application.MemberService;
import com.campro.member.application.request.MemberLoginRequest;
import com.campro.member.application.request.MemberSignupRequest;
import com.campro.member.presentation.controller.response.MemberLoginResponse;
import com.campro.member.presentation.controller.response.MemberSignupResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<MemberSignupResponse>> signup(
            @Valid @RequestBody MemberSignupRequest memberSignupRequest
    ) {
        MemberSignupResponse response = memberService.signup(memberSignupRequest);
        return ResponseEntity.ok().body(
                ApiResponse.from(
                        ResponseCode.SIGNUP_SUCCESS.getCode(),
                        ResponseCode.SIGNUP_SUCCESS.getMessage(),
                        response
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<MemberLoginResponse>> login(
            @RequestBody MemberLoginRequest memberLoginRequest
    ) {
        MemberLoginResponse response = memberService.login(memberLoginRequest);
        return ResponseEntity.ok().body(
                ApiResponse.from(
                        ResponseCode.LOGIN_SUCCESS.getCode(),
                        ResponseCode.LOGIN_SUCCESS.getMessage(),
                        response
                )
        );
    }
}
