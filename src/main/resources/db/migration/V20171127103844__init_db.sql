-- Wave One
CREATE TABLE user_status
(
  user_status_id SERIAL PRIMARY KEY,
  title          VARCHAR(40) NOT NULL
);

CREATE TABLE user_type
(
  user_type_id SERIAL PRIMARY KEY,
  title        VARCHAR(40) NOT NULL
);

CREATE TABLE order_status
(
  order_status_id SERIAL PRIMARY KEY,
  title           VARCHAR(40) NOT NULL
);

CREATE TABLE order_type
(
  order_type_id SERIAL PRIMARY KEY,
  title         VARCHAR(40) NOT NULL
);

CREATE TABLE product_status
(
  product_status_id SERIAL PRIMARY KEY,
  title             VARCHAR(40) NOT NULL
);

CREATE TABLE product_type
(
  product_type_id SERIAL PRIMARY KEY,
  title           VARCHAR(40) NOT NULL
);

CREATE TABLE country
(
  country_id SERIAL PRIMARY KEY,
  title      VARCHAR(40) NOT NULL
);

-- Wave Two
CREATE TABLE product
(
  product_id        SERIAL PRIMARY KEY,
  title             VARCHAR(40) NOT NULL,
  product_status_id INTEGER     NOT NULL,
  product_type_id   INTEGER     NOT NULL,
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
  title        VARCHAR(40) NOT NULL,
  abbreviation CHAR(2)     NOT NULL,
  country_id   INTEGER,
  CONSTRAINT fk_state_country
  FOREIGN KEY (country_id)
  REFERENCES country (country_id)
);

CREATE TABLE "user"
(
  user_id        SERIAL PRIMARY KEY,
  first_name     VARCHAR(40) NOT NULL,
  last_name      VARCHAR(40) NOT NULL,
  user_status_id INTEGER,
  user_type_id   INTEGER,
  state_id       INTEGER,
  CONSTRAINT fk_user_user_status
  FOREIGN KEY (user_status_id)
  REFERENCES user_status (user_status_id),
  CONSTRAINT fk_user_user_type
  FOREIGN KEY (user_type_id)
  REFERENCES user_type (user_type_id),
  CONSTRAINT fk_user_state
  FOREIGN KEY (state_id)
  REFERENCES state (state_id)
);

-- Wave Three

CREATE TABLE "order"
(
  order_id        SERIAL PRIMARY KEY,
  title           VARCHAR(40) NOT NULL,
  order_status_id INTEGER,
  order_type_id   INTEGER,
  state_id        INTEGER,
  user_id         INTEGER,
  CONSTRAINT fk_order_order_status
  FOREIGN KEY (order_status_id)
  REFERENCES order_status (order_status_id),
  CONSTRAINT fk_order_order_type
  FOREIGN KEY (order_type_id)
  REFERENCES order_type (order_type_id),
  CONSTRAINT fk_order_state
  FOREIGN KEY (state_id)
  REFERENCES state (state_id),
  CONSTRAINT fk_order_user
  FOREIGN KEY (user_id)
  REFERENCES "user" (user_id)
);

-- Wave Four

CREATE TABLE order_line
(
  order_id   INTEGER,
  product_id INTEGER,
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
  message       TEXT,
  order_id      INTEGER,
  CONSTRAINT fk_order_note_order
  FOREIGN KEY (order_id)
  REFERENCES "order" (order_id)
);