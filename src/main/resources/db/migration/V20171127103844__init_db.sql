-- Wave One
CREATE TABLE user_type
(
  user_type_id SERIAL PRIMARY KEY,
  title        VARCHAR(40)
);

CREATE TABLE order_status
(
  order_status_id SERIAL PRIMARY KEY,
  title           VARCHAR(40)
);

CREATE TABLE customer_type
(
  customer_type_id SERIAL PRIMARY KEY,
  title            VARCHAR(40)
);

CREATE TABLE product_status
(
  product_status_id SERIAL PRIMARY KEY,
  title             VARCHAR(40)
);

CREATE TABLE product_type
(
  product_type_id SERIAL PRIMARY KEY,
  title           VARCHAR(40)
);

CREATE TABLE country
(
  country_id SERIAL PRIMARY KEY,
  title      VARCHAR(40)
);

-- Wave Two
CREATE TABLE product
(
  product_id        SERIAL PRIMARY KEY,
  title             VARCHAR(40),
  product_status_id INTEGER,
  product_type_id   INTEGER,
  price             NUMERIC(8, 2),
  CONSTRAINT fk_product_product_status
  FOREIGN KEY (product_status_id)
  REFERENCES product_status (product_status_id),
  CONSTRAINT fk_product_product_type
  FOREIGN KEY (product_type_id)
  REFERENCES product_type (product_type_id)
);

CREATE TABLE state
(
  state_id     SERIAL PRIMARY KEY,
  title        VARCHAR(40),
  abbreviation CHAR(2),
  country_id   INTEGER,
  CONSTRAINT fk_state_country
  FOREIGN KEY (country_id)
  REFERENCES country (country_id)
);

CREATE TABLE "user"
(
  user_id         SERIAL PRIMARY KEY,
  first_name      VARCHAR(40),
  last_name       VARCHAR(40),
  phone_number    VARCHAR(10),
  email_address   VARCHAR(255),
  address_one     VARCHAR(80),
  address_two     VARCHAR(80) NULL,
  city            VARCHAR(40),
  postal_zip_code VARCHAR(15),
  user_type_id    INTEGER,
  state_id        INTEGER,
  CONSTRAINT fk_user_user_type
  FOREIGN KEY (user_type_id)
  REFERENCES user_type (user_type_id),
  CONSTRAINT fk_user_state
  FOREIGN KEY (state_id)
  REFERENCES state (state_id)
);

-- Wave Three
CREATE TABLE customer
(
  customer_id      SERIAL PRIMARY KEY,
  first_name       VARCHAR(40),
  last_name        VARCHAR(40),
  phone_number     VARCHAR(10),
  email_address    VARCHAR(255),
  address_one      VARCHAR(80),
  address_two      VARCHAR(80) NULL,
  city             VARCHAR(40),
  postal_zip_code  VARCHAR(15),
  state_id         INTEGER,
  customer_type_id INTEGER,
  CONSTRAINT fk_customer_state
  FOREIGN KEY (state_id)
  REFERENCES state (state_id),
  CONSTRAINT fk_customer_customer_type
  FOREIGN KEY (customer_type_id)
  REFERENCES customer_type (customer_type_id)
);

-- Wave Four
CREATE TABLE "order"
(
  order_id        SERIAL PRIMARY KEY,
  title           VARCHAR(40),
  order_status_id INTEGER,
  user_id         INTEGER,
  customer_id     INTEGER,
  total           NUMERIC(8, 2),
  CONSTRAINT fk_order_order_status
  FOREIGN KEY (order_status_id)
  REFERENCES order_status (order_status_id),
  CONSTRAINT fk_order_user
  FOREIGN KEY (user_id)
  REFERENCES "user" (user_id),
  CONSTRAINT fk_order_customer
  FOREIGN KEY (customer_id)
  REFERENCES customer (customer_id)
);

-- Wave Five
CREATE TABLE order_line
(
  order_id         INTEGER,
  product_id       INTEGER,
  product_quantity INTEGER,
  total            INTEGER,
  CONSTRAINT pk_order_line
  PRIMARY KEY (order_id, product_id),
  CONSTRAINT fk_order_line_order
  FOREIGN KEY (order_id)
  REFERENCES "order" (order_id),
  CONSTRAINT fk_order_line_product
  FOREIGN KEY (product_id)
  REFERENCES product (product_id)
);

CREATE TABLE order_note
(
  order_note_id SERIAL PRIMARY KEY,
  title         VARCHAR(40),
  message       TEXT,
  order_id      INTEGER,
  created_at    TIMESTAMP DEFAULT NOW(),
  CONSTRAINT fk_order_note_order
  FOREIGN KEY (order_id)
  REFERENCES "order" (order_id)
);