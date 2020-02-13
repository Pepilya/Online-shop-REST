--liquibase formatted sql
--changeset TestUsers_sql:13
create table user_table (user_id SERIAL PRIMARY KEY, user_name varchar(30) not null, user_email varchar(30) not null);
