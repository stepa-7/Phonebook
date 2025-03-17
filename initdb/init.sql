CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       full_name VARCHAR(255),
                       phone_number VARCHAR(255),
                       note VARCHAR(255)
);

ALTER SEQUENCE users_id_seq RESTART WITH 1;

GRANT CONNECT ON DATABASE phonebook TO phonebook_user;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO phonebook_user;
REVOKE CREATE ON DATABASE phonebook FROM phonebook_user;
REVOKE CREATE ON SCHEMA public FROM phonebook_user;
-- REVOKE USAGE ON SCHEMA public FROM phonebook_user;