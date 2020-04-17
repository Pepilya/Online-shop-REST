--liquibase formatted sql
--changeset TestUsers_sql:16
CREATE TABLE users
(
id             SERIAL UNIQUE PRIMARY KEY NOT NULL,
login          TEXT    NOT NULL,
password       TEXT    NOT NULL
);

CREATE TABLE roles
(
id             SERIAL PRIMARY KEY,
name           TEXT NOT NULL
);

CREATE TABLE user_role
(
user_id INT REFERENCES users ON DELETE CASCADE,
role_id INT REFERENCES roles ON DELETE CASCADE
);