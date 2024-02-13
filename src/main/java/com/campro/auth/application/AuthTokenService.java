package com.campro.auth.application;

import com.campro.member.domain.Member;

import java.time.Duration;
import java.util.Date;
import java.util.Optional;

public interface AuthTokenService {
    String generateAccessToken(Member member, Date now, Optional<Duration> empty);
    String generateRefreshToken(Member member, Date now, Optional<Duration> empty);
    boolean validAccessToken(String token);
    boolean validRefreshToken(String token);
    String getUserEmail(String token);
}
