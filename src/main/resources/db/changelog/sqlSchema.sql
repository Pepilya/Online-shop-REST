--liquibase formatted sql
--changeset TestUsers_sql:16
CREATE TABLE user_table (user_id SERIAL PRIMARY KEY, user_name VARCHAR (30), user_email VARCHAR (30));

--changeset TestUsers_sql:17
DROP TABLE user_table;

--changeset TestUsers_sql:18
CREATE TABLE user_table (user_id SERIAL PRIMARY KEY, user_name VARCHAR (30) NOT NULL, user_email VARCHAR (30) NOT NULL);