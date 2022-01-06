CREATE TABLE courses
(
    id          BIGSERIAL PRIMARY KEY,
    course_id BIGINT NOT NULL ,
    name        TEXT        NOT NULL,
    price       INT         NOT NULL CHECK ( price >= 0 ),
    image       TEXT        NOT NULL,
    numberseats INT         NOT NULL CHECK ( numberseats >= 0 ) DEFAULT 0,
    removed     BOOL        NOT NULL                            DEFAULT FALSE,
    created     timestamptz NOT NULL                            DEFAULT CURRENT_TIMESTAMP

);

CREATE TABLE registrations
(
    id            BIGSERIAL PRIMARY KEY,
    course_id BIGINT NOT NULL ,
    name          TEXT        NOT NULL,
    price         INT         NOT NULL CHECK ( price >= 0 ),
    customer_name TEXT        NOT NULL,
    removed       BOOL        NOT NULL DEFAULT FALSE,
    created       timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP
);
