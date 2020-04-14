
CREATE TABLE users (
            id SERIAL PRIMARY KEY,
            first_name VARCHAR(100) NOT NULL,
            last_name VARCHAR(100) NOT NULL,
            username VARCHAR(100) NOT NULL,
            email_id VARCHAR(100)  NOT NULL,
            password_hash VARCHAR(200)  NOT NULL,
            CONSTRAINT email_unique UNIQUE (email_id),
            CONSTRAINT username_unique UNIQUE (username)
            );


CREATE TABLE client(
            id SERIAL PRIMARY KEY,
            user_id INT NOT NULL,
            name VARCHAR(100)  NOT NULL,
            key VARCHAR(100)  NOT NULL,
            CONSTRAINT key_unique UNIQUE (key),
            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
            );