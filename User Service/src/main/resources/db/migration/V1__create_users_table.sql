CREATE TABLE users (
                       id BIGINT NOT NULL AUTO_INCREMENT,

                       email VARCHAR(255) NOT NULL,
                       username VARCHAR(100) NOT NULL,

                       first_name VARCHAR(100) NOT NULL,
                       last_name VARCHAR(100) NOT NULL,

                       password VARCHAR(255) NOT NULL,

                       role VARCHAR(30) NOT NULL DEFAULT 'USER',
                       status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',

                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
                           ON UPDATE CURRENT_TIMESTAMP,

                       PRIMARY KEY (id),

                       CONSTRAINT uk_users_email
                           UNIQUE (email),

                       CONSTRAINT uk_users_username
                           UNIQUE (username)
);