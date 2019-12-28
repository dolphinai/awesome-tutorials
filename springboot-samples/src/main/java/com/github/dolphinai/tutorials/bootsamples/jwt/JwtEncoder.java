package com.github.dolphinai.tutorials.bootsamples.jwt;

import com.github.dolphinai.tutorials.bootsamples.common.AppRuntimeException;
import io.jsonwebtoken.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
public final class JwtEncoder implements DisposableBean {

    @Setter
    private SecretKey secretKey;
    @Setter
    private String audience;
    @Setter
    private String issuer;


    public String encode(String subject) {
        return encode(subject, 0);
    }

    public String encode(String subject, int timeoutSeconds) {
        return encode(subject, timeoutSeconds,
                jwtBuilder -> {
                });
    }

    public String encode(String subject, int timeoutSeconds, Consumer<JwtBuilder> consumer) {
        Objects.requireNonNull(subject);
        long milliseconds = System.currentTimeMillis();
        Date now = new Date(milliseconds);
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setAudience(this.audience)
                .setIssuer(this.issuer)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS512, this.secretKey);
        if (timeoutSeconds > 0) {
            long expMillis = milliseconds + timeoutSeconds * 1000;
            builder.setExpiration(new Date(expMillis));
        }
        // consumer
        consumer.accept(builder);
        return builder.compact();
    }

    public Optional<Claims> decodeToClaims(String jwt) {
        Objects.requireNonNull(jwt);
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.secretKey)
                    .requireIssuer(this.issuer)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (JwtException e) {
            log.error("[decodeToClaims()] Failed to parse JWT", e);
        }
        return Optional.ofNullable(claims);
    }

    public String decode(String jwt) {
        Optional<Claims> claims = decodeToClaims(jwt);
        return claims.isPresent() ? claims.get().getSubject() : null;
    }

    @Override
    public void destroy() throws Exception {
        this.secretKey = null;
    }

    public static JwtEncoder of(String key, String issuer) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(issuer);

        // secret key
        KeyGenerator generator = null;
        SecureRandom secureRandom = null;
        try {
            generator = KeyGenerator.getInstance("AES");
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new AppRuntimeException(e);
        }
        secureRandom.setSeed(key.getBytes());
        generator.init(128, secureRandom);
        SecretKey secretKey = generator.generateKey();

        JwtEncoder encoder = new JwtEncoder();
        encoder.setSecretKey(secretKey);
        encoder.setIssuer(issuer);
        return encoder;
    }

    public static void main(String[] args) {
        JwtEncoder encoder = JwtEncoder.of("123456678", "open");
        for(int i = 0; i< 10; i++) {
            String jwt = encoder.encode("WONG");
            System.out.println(jwt);
            System.out.println(encoder.decode(jwt));
        }
    }
}
