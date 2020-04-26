--liquibase formatted sql
--changeset TestUsers_sql:16
CREATE TABLE users
(
    id       SERIAL UNIQUE PRIMARY KEY NOT NULL,
    login    TEXT   UNIQUE             NOT NULL,
    password TEXT                      NOT NULL
);

CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE user_role
(
    user_id INT REFERENCES users ON DELETE CASCADE,
    role_id INT REFERENCES roles ON DELETE CASCADE
);

CREATE TABLE storage
(
    id    SERIAL UNIQUE PRIMARY KEY NOT NULL,
    name  TEXT                      NOT NULL,
    price INT                       NOT NULL,
    count_product INT               NOT NULL
);

CREATE TABLE deposit
(
    user_id INT REFERENCES users ON DELETE CASCADE,
    sum_deposit INT DEFAULT 0 NOT NULL
);

CREATE TABLE basket
(
    id         SERIAL UNIQUE PRIMARY KEY NOT NULL,
    user_id    INT REFERENCES users,
    product_id INT REFERENCES storage,
    status     TEXT DEFAULT 'OPEN'
);