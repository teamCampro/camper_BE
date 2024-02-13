package com.campro.member.application;

import com.campro.auth.application.AuthTokenService;
import com.campro.common.exception.ApiException;
import com.campro.common.exception.ErrorCode;
import com.campro.encrypt.application.service.EncryptService;
import com.campro.member.application.request.MemberLoginRequest;
import com.campro.member.application.request.MemberSignupRequest;
import com.campro.member.domain.Member;
import com.campro.member.infrastructure.MemberRepository;
import com.campro.member.presentation.controller.response.MemberLoginResponse;
import com.campro.member.presentation.controller.response.MemberSignupResponse;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AuthTokenService authTokenService;
    private final EncryptService encryptService;

    public MemberServiceImpl(MemberRepository memberRepository,
                             AuthTokenService authTokenService,
                             EncryptService encryptService
    ) {
        this.memberRepository = memberRepository;
        this.authTokenService = authTokenService;
        this.encryptService = encryptService;
    }

    @Override
    public MemberSignupResponse signup(MemberSignupRequest memberSignupRequest) {
        String nickname = memberSignupRequest.nickname();
        String email = memberSignupRequest.email();

        verifyDuplicateNickname(nickname);
        verifyDuplicateEmail(email);

        String encryptedPassword = encryptService.unrecoverableEncryptData(memberSignupRequest.password());
        Member member = Member.from(nickname, email, encryptedPassword);

        memberRepository.save(member);
        return MemberSignupResponse.from(member.nickname());
    }

    @Override
    public MemberLoginResponse login(MemberLoginRequest memberLoginRequest) {
        Member member = memberRepository.findByEmail(memberLoginRequest.email())
                .orElseThrow(() -> new ApiException(
                        ErrorCode.LOGIN_FAIL_BY_NOT_EXIST_EMAIL.getCode(),
                        ErrorCode.LOGIN_FAIL_BY_NOT_EXIST_EMAIL.getMessage()
                ));

        verifyPassword(memberLoginRequest, member);

        Date loginDate = new Date();


        String accessToken = authTokenService.generateAccessToken(member, loginDate, Optional.empty());
        String refreshToken = authTokenService.generateRefreshToken(member, loginDate, Optional.empty());

        return MemberLoginResponse.from(member.email(), accessToken, refreshToken);
    }

    private void verifyPassword(MemberLoginRequest memberLoginRequest, Member member) {
        if (!encryptService.matchData(memberLoginRequest.password(), member.password())) {
            throw new ApiException(
                    ErrorCode.LOGIN_FAIL_BY_PASSWORD_MISMATCH.getCode(),
                    ErrorCode.LOGIN_FAIL_BY_PASSWORD_MISMATCH.getMessage()
            );
        }
    }

    private void verifyDuplicateEmail(String email) {
        if (isEmailExist(email)) {
            throw new ApiException(
                    ErrorCode.SIGNUP_FAIL_BY_DUPLICATE_EMAIL.getCode(),
                    ErrorCode.SIGNUP_FAIL_BY_DUPLICATE_EMAIL.getMessage()
            );
        }
    }

    private void verifyDuplicateNickname(String nickname) {
        if (isNicknameExist(nickname)) {
            throw new ApiException(
                    ErrorCode.SIGNUP_FAIL_BY_DUPLICATE_NICKNAME.getCode(),
                    ErrorCode.SIGNUP_FAIL_BY_DUPLICATE_NICKNAME.getMessage()
            );
        }
    }

    private boolean isEmailExist(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    private boolean isNicknameExist(String nickname) {
        return memberRepository.findByNickname(nickname).isPresent();
    }
}
