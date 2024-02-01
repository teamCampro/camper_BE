CREATE TABLE IF NOT EXISTS onboarding (
    board_id SERIAL PRIMARY KEY,
    question VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS choice (
    id SERIAL PRIMARY KEY,
    board_id INTEGER,
    text VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS keyword (
    id SERIAL PRIMARY KEY,
    text VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS member (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255),
    password INTEGER,
    image VARCHAR(255),
    nickname VARCHAR(255),
    type VARCHAR(255),
    enroll_date DATE,
    update_date DATE
);


TRUNCATE TABLE onboarding RESTART IDENTITY;
TRUNCATE TABLE choice RESTART IDENTITY;
TRUNCATE TABLE keyword RESTART IDENTITY;
