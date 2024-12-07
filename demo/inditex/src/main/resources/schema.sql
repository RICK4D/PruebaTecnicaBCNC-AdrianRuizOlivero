CREATE SCHEMA INDITEX;
SET SCHEMA INDITEX;

-- Crear la tabla BRANDS
CREATE TABLE BRANDS (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(50) NOT NULL
);

-- Crear la tabla PRODUCT
CREATE TABLE PRODUCTS (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(50) NOT NULL,
    DESCRIPTION VARCHAR(255),
    CATEGORY VARCHAR(50)
);

-- Crear la tabla PRICES con claves foráneas
CREATE TABLE PRICES (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    BRAND_ID BIGINT NOT NULL,
    PRODUCT_ID BIGINT NOT NULL,
    START_DATE TIMESTAMP NOT NULL,
    END_DATE TIMESTAMP NOT NULL,
    PRICE_LIST INT NOT NULL,
    PRIORITY INT NOT NULL,
    PRICE DECIMAL(10,2) NOT NULL,
    CURRENCY VARCHAR(5) NOT NULL,
    CONSTRAINT FK_BRAND FOREIGN KEY (BRAND_ID) REFERENCES BRANDS(ID),
    CONSTRAINT FK_PRODUCT FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS(ID)
);

CREATE INDEX IDX_BRAND ON PRICES (BRAND_ID);
CREATE INDEX IDX_PRODUCT ON PRICES (PRODUCT_ID);
