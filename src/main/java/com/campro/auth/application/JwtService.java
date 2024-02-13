package com.campro.auth.application;

import com.campro.auth.config.JwtProperties;
import com.campro.auth.domain.Token;
import com.campro.auth.infrastructure.TokenRepository;
import com.campro.member.domain.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.Optional;

@Service
public class JwtService implements AuthTokenService {

    private final JwtProperties jwtProperties;
    private final TokenRepository tokenRepository;
    private static final String MEMBER_CLAIM = "email";

    public JwtService(JwtProperties jwtProperties, TokenRepository tokenRepository) {
        this.jwtProperties = jwtProperties;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public String generateAccessToken(Member member, Date now, Optional<Duration> empty) {
        Duration expiration = empty.orElseGet(
                () -> Duration.ofDays(jwtProperties.accessExpiredAt())
        );
        Date expiredAt = calculateExpiredAt(now, expiration);
        return makeToken(now, expiredAt, member);
    }

    @Override
    public String generateRefreshToken(Member member, Date now, Optional<Duration> empty) {
        Duration expiration = empty.orElseGet(
                () -> Duration.ofDays(jwtProperties.refreshExpiredAt())
        );
        Date expiredAt = calculateExpiredAt(now, expiration);
        String refreshToken = makeToken(now, expiredAt, member);

        tokenRepository.save(Token.from(member.id(), refreshToken));

        return refreshToken;
    }

    @Override
    public boolean validAccessToken(String token) {
        byte[] decodedKey = keyDecoder(jwtProperties.accessSecretKey());
        return validToken(token, decodedKey);
    }

    @Override
    public boolean validRefreshToken(String token) {
        byte[] decodedKey = keyDecoder(jwtProperties.refreshSecretKey());
        return validToken(token, decodedKey);
    }

    @Override
    public String getUserEmail(String token) {
        Claims claims = getClaims(token);
        return claims.get(MEMBER_CLAIM, String.class);
    }

    private static Date calculateExpiredAt(Date now, Duration expiration) {
        return new Date(now.getTime() + expiration.toMillis());
    }

    private String makeToken(Date now, Date expiredAt, Member member) {
        byte[] decodedKey = keyDecoder(jwtProperties.accessSecretKey());
        SecretKey key = keyEncryption(decodedKey);
        return Jwts.builder()
                .issuer(jwtProperties.issuer())
                .issuedAt(now)
                .expiration(expiredAt)
                .subject(member.nickname())
                .claim(MEMBER_CLAIM, member.email())
                .signWith(key)
                .compact();
    }

    private byte[] keyDecoder(String secretKey) {
        return Decoders.BASE64.decode(secretKey);
    }

    private SecretKey keyEncryption(byte[] decodedKey) {
        return Keys.hmacShaKeyFor(decodedKey);
    }

    private boolean validToken(String token, byte[] decodedKey) {
        SecretKey key = keyEncryption(decodedKey);
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException exception) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        byte[] decodedKey = keyDecoder(jwtProperties.accessSecretKey());
        SecretKey key = keyEncryption(decodedKey);
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
