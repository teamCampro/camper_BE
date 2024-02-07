package com.campro.member.application;

import com.campro.member.application.request.MemberSignupRequest;
import com.campro.member.presentation.controller.response.MemberSignupResponse;


public interface MemberService {
    MemberSignupResponse signup(MemberSignupRequest memberSignupRequest);
}