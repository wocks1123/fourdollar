-- 카테고리

CREATE SEQUENCE category_id_seq START 1 INCREMENT 50;

CREATE TABLE category
(
    id            BIGINT PRIMARY KEY DEFAULT nextval('category_id_seq'),
    parent_id     BIGINT,
    name          VARCHAR(100) NOT NULL,
    description   TEXT,
    level         INTEGER      NOT NULL CHECK (level IN (1, 2, 3)),
    display_order INTEGER      NOT NULL
);
