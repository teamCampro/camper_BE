package com.campro.mock;

import com.campro.auth.application.AuthTokenService;
import com.campro.member.domain.Member;

import java.time.Duration;
import java.util.Date;
import java.util.Optional;

public class FakeTokenService implements AuthTokenService {
    @Override
    public String generateAccessToken(Member member, Date now, Optional<Duration> empty) {
        return null;
    }

    @Override
    public String generateRefreshToken(Member member, Date now, Optional<Duration> empty) {
        return null;
    }

    @Override
    public boolean validAccessToken(String token) {
        return false;
    }

    @Override
    public boolean validRefreshToken(String token) {
        return false;
    }

    @Override
    public String getUserEmail(String token) {
        return null;
    }
}
