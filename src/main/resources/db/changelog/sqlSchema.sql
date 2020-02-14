--liquibase formatted sql
--changeset TestUsers_sql:13
CREATE TABLE user_table (user_id SERIAL PRIMARY KEY, user_name VARCHAR (30), user_email VARCHAR (30));
