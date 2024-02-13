DROP TABLE IF EXISTS onboarding;
DROP TABLE IF EXISTS choice;
DROP TABLE IF EXISTS keyword;
DROP TABLE IF EXISTS campsite_keyword;
DROP TABLE IF EXISTS campsite_img;
DROP TABLE IF EXISTS campsite;


CREATE TABLE onboarding (
    board_id SERIAL PRIMARY KEY,
    question VARCHAR(255)
);

CREATE TABLE choice (
    id SERIAL PRIMARY KEY,
    board_id INTEGER,
    text VARCHAR(255)
);

CREATE TABLE keyword (
    id SERIAL PRIMARY KEY,
    text VARCHAR(255)
);

CREATE TABLE campsite (
    id SERIAL PRIMARY KEY,
    owner_id VARCHAR(100),
    name VARCHAR(100),
    line_intro VARCHAR(255),
    intro VARCHAR(255),
    feature VARCHAR(255),
    induty VARCHAR(255),
    addr VARCHAR(255),
    addr1 VARCHAR(255),
    map_x NUMERIC(8,5),
    map_y NUMERIC(8,5),
    tel VARCHAR(100),
    homepage VARCHAR(255),
    reserve_url VARCHAR(255),
    reserve_app VARCHAR(255),
    gnrl_site INTEGER,
    auto_site INTEGER,
    glamp_site INTEGER,
    carav_site INTEGER,
    oper_period VARCHAR(100),
    oper_date VARCHAR(100),
    sbrs_cl VARCHAR(255),
    posbl_fclty_cl VARCHAR(255),
    thema VARCHAR(255),
    animal_cmg VARCHAR(255),
    first_img_url VARCHAR(255),
    likes INTEGER,
    star NUMERIC(2,1),
    create_at DATE,
    update_at DATE

);

CREATE TABLE campsite_keyword (
    id SERIAL PRIMARY KEY,
    campsite_id INTEGER REFERENCES campsite(id),
    keyword VARCHAR(255),
    count INTEGER
);

CREATE TABLE campsite_img (
    id SERIAL PRIMARY KEY,
    campsite_id INTEGER REFERENCES campsite(id),
    origin_name VARCHAR(255),
    upload_url VARCHAR(255),
    create_at DATE
);


