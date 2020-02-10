--liquibase formatted sql
--changeset TestUsers_sql:13
create table user_table (user_id integer not null, user_name character varying not null, user_email character varying not null);

--changeset TestUsers_sql:14

insert into user_table (user_id, user_name, user_email) values (1, 'Ilia', 'ilia@mail.com');