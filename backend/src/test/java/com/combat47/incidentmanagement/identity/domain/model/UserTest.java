package com.combat47.incidentmanagement.identity.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void shouldNormalizeEmailAndAssignViewerRoleOnRegistration() {

        User user = User.register(
                " Amir.Jahazi@Example.com ",
                "hashed-password",
                "Amirhossein",
                "Jahazi"
        );

        assertThat(user.getEmail())
                .isEqualTo("amir.jahazi@example.com");

        assertThat(user.getRoles())
                .containsExactly(Role.VIEWER);

        assertThat(user.isEnabled()).isTrue();

        assertThat(user.getPasswordHash())
                .isEqualTo("hashed-password");
    }

    @Test
    void shouldAllowRoleAssignmentAndRemoval() {

        User user = User.register(
                "user@example.com",
                "hashed-password",
                "Test",
                "User"
        );

        user.assignRole(Role.RESPONDER);

        assertThat(user.getRoles())
                .contains(Role.VIEWER, Role.RESPONDER);

        user.removeRole(Role.RESPONDER);

        assertThat(user.getRoles())
                .containsExactly(Role.VIEWER);
    }

    @Test
    void shouldAllowDisablingAndEnablingUser() {

        User user = User.register(
                "user@example.com",
                "hashed-password",
                "Test",
                "User"
        );

        user.disable();

        assertThat(user.isEnabled()).isFalse();

        user.enable();

        assertThat(user.isEnabled()).isTrue();
    }
}