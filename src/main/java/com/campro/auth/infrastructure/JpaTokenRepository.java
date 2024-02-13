package com.campro.auth.infrastructure;

import com.campro.auth.infrastructure.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTokenRepository extends JpaRepository<TokenEntity, Long> {
}
