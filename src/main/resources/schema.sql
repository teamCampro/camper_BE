CREATE TABLE if NOT exists onboarding(board_id serial PRIMARY KEY, question VARCHAR(255));

CREATE TABLE if NOT exists choice(id serial PRIMARY KEY, board_id integer, text VARCHAR(255));

CREATE TABLE if NOT exists keyword(id serial PRIMARY KEY, text VARCHAR(255));

CREATE TABLE if NOT exists member(id serial PRIMARY KEY, email VARCHAR(255),
    password INTEGER, image VARCHAR(255), nickname VARCHAR(255),
    type VARCHAR(255), enroll_date Date, update_date Date
);

CREATE TABLE if NOT exists member_keyword(id serial PRIMARY KEY, member_id integer,  keyword VARCHAR(255));

TRUNCATE TABLE onboarding RESTART IDENTITY ;
TRUNCATE TABLE choice RESTART IDENTITY;
TRUNCATE TABLE keyword RESTART IDENTITY;
