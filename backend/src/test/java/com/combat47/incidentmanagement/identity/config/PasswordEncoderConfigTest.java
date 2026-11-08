package com.combat47.incidentmanagement.identity.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordEncoderConfigTest {

    private final PasswordEncoder passwordEncoder =
            new PasswordEncoderConfig().passwordEncoder();

    @Test
    void shouldEncodePasswordWithoutStoringRawPassword() {

        String rawPassword = "StrongPassword123!";

        String encodedPassword = passwordEncoder.encode(rawPassword);

        assertThat(encodedPassword).isNotEqualTo(rawPassword);

        assertThat(passwordEncoder.matches(
                rawPassword,
                encodedPassword
        )).isTrue();
    }

    @Test
    void shouldGenerateDifferentHashesForSamePassword() {

        String rawPassword = "StrongPassword123!";

        String firstHash = passwordEncoder.encode(rawPassword);
        String secondHash = passwordEncoder.encode(rawPassword);

        assertThat(firstHash).isNotEqualTo(secondHash);
    }

    @Test
    void shouldRejectIncorrectPassword() {

        String encodedPassword =
                passwordEncoder.encode("CorrectPassword123!");

        assertThat(passwordEncoder.matches(
                "WrongPassword123!",
                encodedPassword
        )).isFalse();
    }
}