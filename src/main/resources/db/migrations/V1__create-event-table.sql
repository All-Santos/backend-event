CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE(
    id UUID DEFAUT gen_random_uuid() PRIMARY KEY,
    title VARCHAR (100) NOT NULL,
    description VARCHAR (255) NOT NULL,
    img_url VARCHAR (100) NOT NULL,
    event_url VARCHAR (100) NOT NULL,
    date TIME STAMP NOT NULL,
    remote BOOLEAN NOT NULL

);