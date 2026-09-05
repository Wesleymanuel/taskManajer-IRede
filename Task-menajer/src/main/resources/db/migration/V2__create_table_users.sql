CREATE TABLE users(
    user_id UUID PRIMARY KEY,
    user_name TEXT not null,
    user_email TEXT NOT NULL UNIQUE,
    user_password TEXT NOT NULL
);