package com.campro.member.application;

import com.campro.auth.application.AuthTokenService;
import com.campro.encrypt.application.service.EncryptService;
import com.campro.member.application.request.MemberSignupRequest;
import com.campro.member.presentation.controller.response.MemberSignupResponse;
import com.campro.member.domain.Member;
import com.campro.member.infrastructure.MemberRepository;
import org.springframework.stereotype.Service;

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
        String email = memberSignupRequest.email();
        verifyDuplicateEmail(email);
        verifyDuplicateNickname(memberSignupRequest.nickname());

        String encryptedEmail = encryptService.recoverableEncryptData(email);
        String encryptedPassword = encryptService.unrecoverableEncryptData(memberSignupRequest.password());
        Member member = Member.from(encryptedPassword, encryptedEmail);

        memberRepository.save(member);

        String accessToken = authTokenService.generateAccessToken(email);
        String refreshToken = authTokenService.generateRefreshToken(email);

        return new MemberSignupResponse(email, accessToken, refreshToken);
    }

    private void verifyDuplicateEmail(String email) {
        if (isEmailExist(email)) {
            // common 병합 후 리펙터링 진행 예정
            throw new RuntimeException("Email already exists");
        }
    }

    private void verifyDuplicateNickname(String nickname) {
        if (isNicknameExist(nickname)) {
            // common 병합 후 리펙터링 진행 예정
            throw new RuntimeException("Nickname already exists");
        }
    }

    private boolean isEmailExist(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    private boolean isNicknameExist(String nickname) {
        return memberRepository.findByNickname(nickname).isPresent();
    }
}
