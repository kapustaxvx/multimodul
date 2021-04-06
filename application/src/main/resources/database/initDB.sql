CREATE TABLE IF NOT EXISTS users
(
    user_id    BIGSERIAL PRIMARY KEY ,
    name  VARCHAR(200) NOT NULL ,
    birth_date TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS task_statuses
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tasks
(
    task_id    BIGSERIAL PRIMARY KEY ,
    status_id BIGINT REFERENCES task_statuses(id),
    creation_date TIMESTAMP NOT NULL ,
    title VARCHAR(200) NOT NULL ,
    expiration_date TIMESTAMP
);