CREATE TABLE product
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    category      VARCHAR(255) NULL,
    title         VARCHAR(255) NULL,
    price DOUBLE NOT NULL,
    `description` VARCHAR(255) NULL,
    image         VARCHAR(255) NULL,
    rating_id     BIGINT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE rating
(
    id    BIGINT AUTO_INCREMENT NOT NULL,
    rate DOUBLE NOT NULL,
    count INT NOT NULL,
    CONSTRAINT pk_rating PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_RATING FOREIGN KEY (rating_id) REFERENCES rating (id);