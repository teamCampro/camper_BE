package com.campro.member.infrastructure.entity;

import com.campro.common.infrastructure.entity.BaseTimeEntity;
import com.campro.member.domain.Member;
import com.campro.member.domain.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class MemberEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nickname;
    String email;
    String password;
    String profileImage;

    @Enumerated(EnumType.STRING)
    Role role;

    protected MemberEntity() {
    }

    private MemberEntity(Long id, String nickname, String email, String password, String profileImage, Role role) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
        this.role = role;
    }

    public static MemberEntity fromModel(Member member) {
        return new MemberEntity(
                member.id(),
                member.nickname(),
                member.email(),
                member.password(),
                member.profileImage(),
                member.role()
        );
    }

    public static Member toModel(MemberEntity memberEntity) {
        return new Member(
                memberEntity.id,
                memberEntity.nickname,
                memberEntity.email,
                memberEntity.password,
                memberEntity.profileImage,
                memberEntity.role
        );
    }
}
