CREATE TABLE user_status
(
  user_status_id SERIAL PRIMARY KEY,
  title VARCHAR(20) NOT NULL
);

CREATE TABLE "user"
(
  user_id        SERIAL PRIMARY KEY,
  first_name     VARCHAR(20) NOT NULL,
  last_name      VARCHAR(20) NOT NULL,
  user_status_id INTEGER,
  CONSTRAINT fk_user_user_status
    FOREIGN KEY (user_status_id)
    REFERENCES user_status (user_status_id)
);