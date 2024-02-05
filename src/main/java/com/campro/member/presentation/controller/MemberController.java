package com.campro.member.presentation.controller;

import com.campro.member.application.MemberService;
import com.campro.member.application.request.MemberSignupRequest;
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

    // common 병합 후 리펙터링 진행 예정
    @PostMapping("/signup")
    public ResponseEntity<MemberSignupResponse> signup(@Valid @RequestBody MemberSignupRequest memberSignupRequest) {
        MemberSignupResponse response = memberService.signup(memberSignupRequest);
        return ResponseEntity.ok().body(response);
    }
}
