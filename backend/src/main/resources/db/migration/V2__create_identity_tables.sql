CREATE TABLE incident_management.users
(
    id UUID PRIMARY KEY,

    email VARCHAR(254) NOT NULL,

    password_hash VARCHAR(255) NOT NULL,

    first_name VARCHAR(100) NOT NULL,

    last_name VARCHAR(100) NOT NULL,

    enabled BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,

    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT uk_users_email UNIQUE (email),

    CONSTRAINT chk_users_email_lowercase
        CHECK (email = LOWER(email))
);

CREATE TABLE incident_management.user_roles
(
    user_id UUID NOT NULL,

    role VARCHAR(30) NOT NULL,

    CONSTRAINT pk_user_roles
        PRIMARY KEY (user_id, role),

    CONSTRAINT fk_user_roles_user
        FOREIGN KEY (user_id)
            REFERENCES incident_management.users (id)
            ON DELETE CASCADE,

    CONSTRAINT chk_user_roles_valid_role
        CHECK (role IN ('ADMIN', 'RESPONDER', 'VIEWER'))
);

CREATE INDEX idx_user_roles_role
    ON incident_management.user_roles (role);