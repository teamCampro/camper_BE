package com.campro.member.infrastructure;

import com.campro.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickname);
}
