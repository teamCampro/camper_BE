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

    String nickname;
    String email;
    String password;
    String profileImage;

    @Enumerated(EnumType.STRING)
    Rule rule;

    protected MemberEntity() {
    }

    private MemberEntity(Long id, String nickname, String email, String password, String profileImage, Rule rule) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
        this.rule = rule;
    }

    public static MemberEntity fromModel(Member member) {
        return new MemberEntity(
                member.id(),
                member.nickname(),
                member.email(),
                member.password(),
                member.profileImage(),
                member.rule()
        );
    }

    public static Member toModel(MemberEntity memberEntity) {
        return new Member(
                memberEntity.id,
                memberEntity.nickname,
                memberEntity.email,
                memberEntity.password,
                memberEntity.profileImage,
                memberEntity.rule
        );
    }
}
