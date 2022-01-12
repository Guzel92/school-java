CREATE TABLE courses
(
    id          BIGSERIAL PRIMARY KEY,
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
    course_id     BIGINT      NOT NULL REFERENCES courses,
    name          TEXT        NOT NULL,
    price         INT         NOT NULL CHECK ( price >= 0 ),
    customer_name TEXT        NOT NULL,
    removed       BOOL        NOT NULL DEFAULT FALSE,
    created       timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE register_positions
(
    id          BIGSERIAL PRIMARY KEY,
    course_id   BIGINT NOT NULL REFERENCES courses,
    register_id BIGINT NOT NULL REFERENCES registrations,
    name        TEXT   NOT NULL,
    price       INT    NOT NULL CHECK ( price >= 0 ),
    qty         INT    NOT NULL CHECK ( qty > 0 ) DEFAULT 1 -- qty - сколько штук купил

);

CREATE VIEW register_stats AS
SELECT rs.course_id, sum(rs.qty) total_qty FROM register_positions rs
GROUP BY rs.course_id;