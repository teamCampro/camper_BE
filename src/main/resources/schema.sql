TRUNCATE TABLE onboarding RESTART IDENTITY ;
TRUNCATE TABLE choice RESTART IDENTITY;
TRUNCATE TABLE keyword RESTART IDENTITY;

CREATE TABLE if NOT exists onboarding(board_id serial PRIMARY KEY, question VARCHAR(255));

CREATE TABLE if NOT exists choice(id serial PRIMARY KEY, board_id integer, text VARCHAR(255));

CREATE TABLE if NOT exists keyword(id serial PRIMARY KEY, text VARCHAR(255));

