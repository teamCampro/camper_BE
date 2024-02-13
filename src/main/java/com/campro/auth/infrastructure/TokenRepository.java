package com.campro.auth.infrastructure;

import com.campro.auth.domain.Token;

public interface TokenRepository {
    void save(Token token);
}
