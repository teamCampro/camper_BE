package com.campro.member.infrastructure;

import com.campro.member.domain.Member;
import com.campro.member.infrastructure.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final JpaMemberRepository jpaMemberRepository;

    public MemberRepositoryImpl(JpaMemberRepository jpaMemberRepository) {
        this.jpaMemberRepository = jpaMemberRepository;
    }

    @Override
    public void save(Member member) {
        jpaMemberRepository.save(MemberEntity.fromModel(member));
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return jpaMemberRepository.findByEmail(email)
                .map(MemberEntity::toModel);
    }

    @Override
    public Optional<Member> findByNickname(String nickname) {
        return jpaMemberRepository.findByNickname(nickname)
                .map(MemberEntity::toModel);
    }

}
