-- 상품

CREATE SEQUENCE product_id_seq START 1 INCREMENT 50;

CREATE TABLE product
(
    id         BIGINT PRIMARY KEY       DEFAULT nextval('product_id_seq'),
    title      VARCHAR(500) NOT NULL,
    image      VARCHAR(1000),
    price      INTEGER,
    mall_name  VARCHAR(100),
    brand      VARCHAR(100),
    maker      VARCHAR(100),
    category1  VARCHAR(100),
    category2  VARCHAR(100),
    category3  VARCHAR(100),
    category4  VARCHAR(100)
);

CREATE INDEX idx_product_seller ON product (seller_id);
CREATE INDEX idx_product_brand ON product (brand_id);
CREATE INDEX idx_product_status ON product (status);


-- 카테고리

CREATE SEQUENCE category_id_seq START 1 INCREMENT 50;

CREATE TABLE category
(
    id            BIGINT PRIMARY KEY DEFAULT nextval('category_id_seq'),
    parent_id     BIGINT,
    name          VARCHAR(100) NOT NULL,
    description   TEXT,
    level         INTEGER      NOT NULL CHECK (level IN (1, 2, 3)),
    display_order INTEGER            DEFAULT 0
);


-- 상품 카테고리

CREATE SEQUENCE product_category_id_seq START 1 INCREMENT 50;

CREATE TABLE product_category
(
    id          BIGINT PRIMARY KEY DEFAULT nextval('product_category_id_seq'),
    product_id  BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    UNIQUE (product_id, category_id)
);


-- 상품 옵션 그룹

CREATE SEQUENCE product_option_group_id_seq START 1 INCREMENT 50;

CREATE TABLE product_option_group
(
    id            BIGINT PRIMARY KEY DEFAULT nextval('product_option_group_id_seq'),
    product_id    BIGINT      NOT NULL,
    name          VARCHAR(50) NOT NULL,
    display_order INTEGER            DEFAULT 0
);


-- 상품 옵션

CREATE SEQUENCE product_option_id_seq START 1 INCREMENT 50;

CREATE TABLE product_option
(
    id               BIGINT PRIMARY KEY DEFAULT nextval('product_option_id_seq'),
    option_group_id  BIGINT       NOT NULL,
    name             VARCHAR(100) NOT NULL,
    additional_price DECIMAL(15, 2)     DEFAULT 0,
    display_order    INTEGER            DEFAULT 0
);


/*-- 태그

CREATE SEQUENCE tag_id_seq START 1 INCREMENT 50;

CREATE TABLE tag
(
    id   BIGINT PRIMARY KEY DEFAULT nextval('tag_id_seq'),
    name VARCHAR(50) NOT NULL
);


-- 상품 태그

CREATE SEQUENCE product_tag_id_seq START 1 INCREMENT 50;

CREATE TABLE product_tag
(
    id         BIGINT PRIMARY KEY DEFAULT nextval('product_tag_id_seq'),
    product_id BIGINT NOT NULL,
    tag_id     BIGINT NOT NULL,
    UNIQUE (product_id, tag_id)
);
*/
