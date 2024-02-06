package com.campro.member.application;

import com.campro.encrypt.application.service.EncryptService;
import com.campro.member.application.request.MemberSignupRequest;
import com.campro.member.presentation.controller.response.MemberSignupResponse;
import com.campro.member.domain.Member;
import com.campro.member.infrastructure.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final EncryptService encryptService;

    public MemberServiceImpl(MemberRepository memberRepository,
                             EncryptService encryptService
    ) {
        this.memberRepository = memberRepository;
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
