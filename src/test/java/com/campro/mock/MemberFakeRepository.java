package com.campro.mock;

import com.campro.member.domain.Member;
import com.campro.member.infrastructure.MemberRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemberFakeRepository implements MemberRepository {

    private final Map<Long, Member> storage = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public void save(Member member) {
        if (storage.get(member.id()) == null || member.id() == 0) {
            Member newMember = new Member(
                    ++sequence,
                    member.nickname(),
                    member.email(),
                    member.password(),
                    member.profileImage(),
                    member.role()
            );
            storage.put(newMember.id(), newMember);
        } else {
            storage.put(member.id(), member);
        }
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return storage.values()
                .stream()
                .filter(value -> value.email().equals(email))
                .findFirst();
    }

    @Override
    public Optional<Member> findByNickname(String nickname) {
        return storage.values()
                .stream()
                .filter(value -> value.nickname().equals(nickname))
                .findFirst();
    }
}
