package com.campro.auth.infrastructure;

import com.campro.auth.domain.Token;
import com.campro.auth.infrastructure.entity.TokenEntity;
import org.springframework.stereotype.Repository;

@Repository
public class TokenRepositoryImpl implements TokenRepository {

    private final JpaTokenRepository jpaTokenRepository;

    public TokenRepositoryImpl(JpaTokenRepository jpaTokenRepository) {
        this.jpaTokenRepository = jpaTokenRepository;
    }

    @Override
    public void save(Token token) {
        jpaTokenRepository.save(TokenEntity.fromModel(token));
    }
}
