CREATE TABLE IF NOT EXISTS onboarding (board_id SERIAL PRIMARY KEY, question varchar(255));

CREATE TABLE IF NOT EXISTS choice (id SERIAL PRIMARY KEY, board_id integer, text varchar(255));

CREATE TABLE IF NOT EXISTS keyword (id SERIAL PRIMARY KEY, text varchar(255));

CREATE TABLE IF NOT EXISTS member (id SERIAL PRIMARY KEY, email varchar(255),
    password integer, image varchar(255), nickname varchar(255),
    type varchar(255), enroll_date date, update_date date
);

CREATE TABLE IF NOT EXISTS member_keyword (id SERIAL PRIMARY KEY, member_id integer, keyword varchar(255));

TRUNCATE TABLE onboarding RESTART IDENTITY;
TRUNCATE TABLE choice RESTART IDENTITY;
TRUNCATE TABLE keyword RESTART IDENTITY;
