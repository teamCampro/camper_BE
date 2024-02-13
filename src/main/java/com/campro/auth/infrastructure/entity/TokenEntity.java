package com.campro.auth.infrastructure.entity;

import com.campro.auth.domain.Token;
import com.campro.common.infrastructure.entity.BaseTimeEntity;
import com.campro.member.infrastructure.entity.MemberEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "token")
public class TokenEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;
    private String token;

    protected TokenEntity() {
    }

    private TokenEntity(Long id, Long memberId, String token) {
        this.id = id;
        this.memberId = memberId;
        this.token = token;
    }

    public static TokenEntity fromModel(Token token) {
        return new TokenEntity(
                token.id(),
                token.memberId(),
                token.data()
        );
    }
}
