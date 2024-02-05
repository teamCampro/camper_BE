package com.campro.member.infrastructure.entity;

import com.campro.member.domain.Member;
import com.campro.member.domain.Rule;
import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String email;
    String nickname;
    String profileImage;
    String password;

    @Enumerated(EnumType.STRING)
    Rule rule;

    protected MemberEntity() {
    }

    private MemberEntity(Long id, String email, String nickname, String profileImage, String password, Rule rule) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.password = password;
        this.rule = rule;
    }

    public static MemberEntity fromModel(Member member) {
        return new MemberEntity(
                member.id(),
                member.email(),
                member.nickname(),
                member.profileImage(),
                member.password(),
                member.rule()
        );
    }

    public static Member toModel(MemberEntity memberEntity) {
        return new Member(
                memberEntity.id,
                memberEntity.email,
                memberEntity.password,
                memberEntity.nickname,
                memberEntity.profileImage,
                memberEntity.rule
        );
    }
}
