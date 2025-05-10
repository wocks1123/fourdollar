-- 상품

CREATE SEQUENCE product_id_seq START 1 INCREMENT 50;

CREATE TABLE product
(
    id                BIGINT PRIMARY KEY       DEFAULT nextval('product_id_seq'),
    name              VARCHAR(200)   NOT NULL,
    slug              VARCHAR(200)   NOT NULL UNIQUE,
    short_description VARCHAR(500),
    full_description  TEXT,
    seller_id         INTEGER,
    brand_id          INTEGER,
    status            VARCHAR(20)    NOT NULL  DEFAULT 'Waiting' CHECK (status IN ('Waiting', 'OnSale', 'OutOfStock', 'Deleted')),
    base_price        DECIMAL(15, 2) NOT NULL,
    currency          VARCHAR(3)     NOT NULL,
    created_at        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
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
    level         INTEGER      NOT NULL CHECK (level IN (1, 2, 3)),
    display_order INTEGER            DEFAULT 0,
    name          VARCHAR(100) NOT NULL,
    description   TEXT
);


-- 상품 카테고리

CREATE SEQUENCE product_category_id_seq START 1 INCREMENT 50;

CREATE TABLE product_category
(
    id          BIGINT PRIMARY KEY DEFAULT nextval('product_category_id_seq'),
    product_id  BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    is_primary  BOOLEAN            DEFAULT FALSE,
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


-- 태그

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
