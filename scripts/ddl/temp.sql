-- Sequence 정의
CREATE SEQUENCE seller_id_seq START 1;
CREATE SEQUENCE brand_id_seq START 1;
CREATE SEQUENCE product_detail_id_seq START 1;
CREATE SEQUENCE product_price_id_seq START 1;
CREATE SEQUENCE category_id_seq START 1;
CREATE SEQUENCE product_category_id_seq START 1;
CREATE SEQUENCE product_option_group_id_seq START 1;
CREATE SEQUENCE product_option_id_seq START 1;
CREATE SEQUENCE product_image_id_seq START 1;
CREATE SEQUENCE tag_id_seq START 1;
CREATE SEQUENCE product_tag_id_seq START 1;
CREATE SEQUENCE member_id_seq START 1;
CREATE SEQUENCE review_id_seq START 1;

-- 판매자(Seller) 테이블
CREATE TABLE seller
(
    id            INTEGER PRIMARY KEY      DEFAULT nextval('seller_id_seq'),
    name          VARCHAR(100) NOT NULL,
    description   TEXT,
    logo_url      VARCHAR(255),
    rating        DECIMAL(3, 2),
    contact_email VARCHAR(100),
    contact_phone VARCHAR(20),
    created_at    TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 브랜드(Brand) 테이블
CREATE TABLE brand
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('brand_id_seq'),
    name        VARCHAR(100) NOT NULL,
    slug        VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    logo_url    VARCHAR(255),
    website     VARCHAR(255)
);

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

-- 상품 상세(Product_Detail) 테이블
CREATE TABLE product_detail
(
    id                INTEGER PRIMARY KEY DEFAULT nextval('product_detail_id_seq'),
    product_id        INTEGER NOT NULL,
    weight            DECIMAL(10, 2),
    dimensions        JSONB,
    materials         TEXT,
    country_of_origin VARCHAR(100),
    warranty_info     TEXT,
    care_instructions TEXT,
    additional_info   JSONB
);

-- 상품 가격(Product_Price) 테이블
CREATE TABLE product_price
(
    id         INTEGER PRIMARY KEY     DEFAULT nextval('product_price_id_seq'),
    product_id INTEGER        NOT NULL,
    base_price DECIMAL(15, 2) NOT NULL,
    sale_price DECIMAL(15, 2),
    cost_price DECIMAL(15, 2),
    currency   VARCHAR(3)     NOT NULL DEFAULT 'KRW',
    tax_rate   DECIMAL(5, 2)           DEFAULT 10.00
);

-- 카테고리(Category) 테이블
CREATE TABLE category
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('category_id_seq'),
    name        VARCHAR(100) NOT NULL,
    slug        VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    parent_id   INTEGER,
    level       INTEGER      NOT NULL CHECK (level IN (1, 2, 3)),
    image_url   VARCHAR(255)
);

-- 상품 카테고리 매핑(Product_Category) 테이블
CREATE TABLE product_category
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('product_category_id_seq'),
    product_id  INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    is_primary  BOOLEAN             DEFAULT FALSE,
    UNIQUE (product_id, category_id)
);

-- 상품 옵션 그룹(Product_Option_Group) 테이블
CREATE TABLE product_option_group
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('product_option_group_id_seq'),
    product_id    INTEGER     NOT NULL,
    name          VARCHAR(50) NOT NULL,
    display_order INTEGER             DEFAULT 0
);

-- 상품 옵션(Product_Option) 테이블
CREATE TABLE product_option
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('product_option_id_seq'),
    option_group_id  INTEGER      NOT NULL,
    name             VARCHAR(100) NOT NULL,
    additional_price DECIMAL(15, 2)      DEFAULT 0,
    sku              VARCHAR(50),
    stock            INTEGER             DEFAULT 0,
    display_order    INTEGER             DEFAULT 0
);

-- 상품 이미지(Product_Image) 테이블
CREATE TABLE product_image
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('product_image_id_seq'),
    product_id    INTEGER      NOT NULL,
    url           VARCHAR(255) NOT NULL,
    alt_text      VARCHAR(200),
    is_primary    BOOLEAN             DEFAULT FALSE,
    display_order INTEGER             DEFAULT 0,
    option_id     INTEGER
);

-- 태그(Tag) 테이블
CREATE TABLE tag
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('tag_id_seq'),
    name VARCHAR(50) NOT NULL,
    slug VARCHAR(50) NOT NULL UNIQUE
);

-- 상품 태그(Product_Tag) 테이블
CREATE TABLE product_tag
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('product_tag_id_seq'),
    product_id INTEGER NOT NULL,
    tag_id     INTEGER NOT NULL,
    UNIQUE (product_id, tag_id)
);

-- 회원(Member) 테이블 (Review 테이블 참조용)
CREATE TABLE member
(
    id         INTEGER PRIMARY KEY      DEFAULT nextval('member_id_seq'),
    username   VARCHAR(50)  NOT NULL UNIQUE,
    email      VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 리뷰(Review) 테이블
CREATE TABLE review
(
    id                INTEGER PRIMARY KEY      DEFAULT nextval('review_id_seq'),
    product_id        INTEGER NOT NULL,
    user_id           INTEGER NOT NULL,
    rating            INTEGER NOT NULL CHECK (rating BETWEEN 1 AND 5),
    title             VARCHAR(200),
    content           TEXT,
    created_at        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    verified_purchase BOOLEAN                  DEFAULT FALSE,
    helpful_votes     INTEGER                  DEFAULT 0
);

-- 업데이트 트리거 생성
CREATE
    OR REPLACE FUNCTION update_updated_at_column()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_at
        = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;

-- product 테이블에 업데이트 트리거 적용
CREATE TRIGGER update_product_updated_at
    BEFORE UPDATE
    ON product
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

-- review 테이블에 업데이트 트리거 적용
CREATE TRIGGER update_review_updated_at
    BEFORE UPDATE
    ON review
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

-- 인덱스 생성
CREATE INDEX idx_product_seller ON product (seller_id);
CREATE INDEX idx_product_brand ON product (brand_id);
CREATE INDEX idx_product_status ON product (status);
CREATE INDEX idx_product_detail_product ON product_detail (product_id);
CREATE INDEX idx_product_price_product ON product_price (product_id);
CREATE INDEX idx_category_parent ON category (parent_id);
CREATE INDEX idx_product_category_product ON product_category (product_id);
CREATE INDEX idx_product_category_category ON product_category (category_id);
CREATE INDEX idx_product_option_group_product ON product_option_group (product_id);
CREATE INDEX idx_product_option_group ON product_option (option_group_id);
CREATE INDEX idx_product_image_product ON product_image (product_id);
CREATE INDEX idx_product_image_option ON product_image (option_id);
CREATE INDEX idx_product_tag_product ON product_tag (product_id);
CREATE INDEX idx_product_tag_tag ON product_tag (tag_id);
CREATE INDEX idx_review_product ON review (product_id);
CREATE INDEX idx_review_user ON review (user_id);
CREATE INDEX idx_review_rating ON review (rating);
