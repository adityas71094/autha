CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
            id UUID DEFAULT uuid_generate_v4 () PRIMARY KEY,
            first_name VARCHAR(100) NOT NULL,
            last_name VARCHAR(100) NOT NULL,
            username VARCHAR(100) NOT NULL,
            email_id VARCHAR(100)  NOT NULL,
            password_hash VARCHAR(200)  NOT NULL,
            enabled BOOLEAN NOT NULL DEFAULT FALSE,
            CONSTRAINT email_unique UNIQUE (email_id),
            CONSTRAINT username_unique UNIQUE (username)
            );

CREATE TABLE authorities(
            id UUID DEFAULT uuid_generate_v4 () PRIMARY KEY,
            user_id UUID NOT NULL,
            authority VARCHAR(100)  NOT NULL,
            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
            );

CREATE TABLE client(
            id UUID DEFAULT uuid_generate_v4 () PRIMARY KEY,
            user_id UUID NOT NULL,
            name VARCHAR(100)  NOT NULL,
            key VARCHAR(100)  NOT NULL,
            CONSTRAINT key_unique UNIQUE (key),
            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
            );