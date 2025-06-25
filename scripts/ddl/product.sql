-- 상품

CREATE SEQUENCE product_id_seq START 1 INCREMENT 50;

CREATE TABLE product
(
    id                BIGINT PRIMARY KEY                DEFAULT nextval('product_id_seq'),
    product_code      VARCHAR(20)              NOT NULL,
    name              VARCHAR(200)             NOT NULL,
    slug              VARCHAR(200)             NOT NULL,
    short_description VARCHAR(500),
    full_description  TEXT,
    base_price        DECIMAL(15, 2)           NOT NULL,
    sale_start_date   TIMESTAMP WITH TIME ZONE NOT NULL,
    sale_end_date     TIMESTAMP WITH TIME ZONE NOT NULL,
    status            VARCHAR(20)              NOT NULL,
    created_at        TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at        TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by        VARCHAR(100),
    updated_by        VARCHAR(100)
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
    display_order INTEGER     NOT NULL
);


-- 상품 옵션

CREATE SEQUENCE product_option_id_seq START 1 INCREMENT 50;

CREATE TABLE product_option
(
    id               BIGINT PRIMARY KEY      DEFAULT nextval('product_option_id_seq'),
    option_group_id  BIGINT         NOT NULL,
    name             VARCHAR(100)   NOT NULL,
    sku              VARCHAR(50)    NOT NULL,
    additional_price DECIMAL(15, 2) NOT NULL DEFAULT 0,
    display_order    INTEGER        NOT NULL
);
