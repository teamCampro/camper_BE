package com.campro.member.infrastructure;

import com.campro.member.infrastructure.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByEmail(String email);
    Optional<MemberEntity> findByNickname(String nickname);
}
