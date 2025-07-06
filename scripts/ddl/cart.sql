CREATE SEQUENCE cart_id_seq START 1 INCREMENT 50;
CREATE SEQUENCE cart_line_item_id_seq START 1 INCREMENT 50;
CREATE SEQUENCE cart_item_id_seq START 1 INCREMENT 50;

CREATE TABLE cart
(
    id         BIGINT PRIMARY KEY       DEFAULT nextval('cart_id_seq'),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cart_line_item
(
    id         BIGINT PRIMARY KEY       DEFAULT nextval('cart_line_item_id_seq'),
    cart_id    BIGINT         NOT NULL,
    product_id BIGINT         NOT NULL,
    quantity   INTEGER        NOT NULL,
    base_price DECIMAL(15, 2) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cart_item
(
    id                BIGINT PRIMARY KEY       DEFAULT nextval('cart_item_id_seq'),
    cart_line_item_id BIGINT         NOT NULL,
    option_group_name VARCHAR(50)    NOT NULL,
    option_value      VARCHAR(100)   NOT NULL,
    additional_price  DECIMAL(15, 2) NOT NULL  DEFAULT 0,
    created_at        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
