CREATE TABLE tasks(
    task_id uuid PRIMARY KEY,
    user_id uuid NOT NULL,
    task_title TEXT NOT NULL UNIQUE,
    task_description TEXT NOT NULL,
    status BOOLEAN NOT NULL
);