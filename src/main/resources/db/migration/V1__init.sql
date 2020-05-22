CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
            id UUID DEFAULT uuid_generate_v4 () PRIMARY KEY,
            first_name VARCHAR(100) NOT NULL,
            last_name VARCHAR(100) NOT NULL,
            username VARCHAR(100) NOT NULL,
            email_id VARCHAR(100)  NOT NULL,
            password_hash VARCHAR(200)  NOT NULL,
            enabled BOOLEAN NOT NULL DEFAULT FALSE,
            acc_locked BOOLEAN NOT NULL DEFAULT FALSE,
            acc_expired BOOLEAN NOT NULL DEFAULT FALSE,
            cred_expired BOOLEAN NOT NULL DEFAULT FALSE,
            CONSTRAINT email_unique UNIQUE (email_id),
            CONSTRAINT username_unique UNIQUE (username)
            );

CREATE TABLE authorities(
            id UUID DEFAULT uuid_generate_v4 () PRIMARY KEY,
            user_id UUID NOT NULL,
            authority VARCHAR(100)  NOT NULL,
            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
            CONSTRAINT user_authority_unique UNIQUE (user_id, authority)
            );


CREATE TABLE client(
            id UUID DEFAULT uuid_generate_v4 () PRIMARY KEY,
            user_id UUID NOT NULL,
            name VARCHAR(100)  NOT NULL,
            key VARCHAR(100)  DEFAULT uuid_generate_v4() NOT NULL,
            CONSTRAINT name_key_unique UNIQUE (name, key),
            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
            );

CREATE TABLE authentication_code(
            id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
            owner_id UUID NOT NULL,
            client_id UUID NOT NULL,
            valid_till TIMESTAMP WITH TIME ZONE,
            FOREIGN KEY (owner_id) REFERENCES users(id) ON DELETE CASCADE,
            FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE
            );