INSERT INTO order_status (title)
VALUES ('Prospect'), ('Information Gathering'), ('Proposal'), ('Negotiating'), ('Closing');

INSERT INTO user_type (title) VALUES ('User'), ('Manager');

INSERT INTO product_status (title) VALUES ('Available'), ('Unavailable');

INSERT INTO product_type (title) VALUES ('Small'), ('Medium'), ('Large');

INSERT INTO customer_type (title)
VALUES ('Health Care'), ('Oil & Gas'), ('Education'), ('Government'), ('Information Technology');

INSERT INTO "user" (first_name, last_name, user_type_id)
VALUES ('Ethan', 'Orcutt', 2), ('Clay', 'Orcutt', 1), ('Tony', 'Orcutt', 1), ('Trevor', 'Orcutt', 1);

-- Assigned to Clay
INSERT INTO "order" (title, order_status_id, user_id)
VALUES ('Home Depot', 1, 2), ('Lowes', 1, 2), ('Walmart', 1, 2), ('BestBuy', 1, 2);

-- Assigned to Tony
INSERT INTO "order" (title, order_status_id, user_id)
VALUES ('Kohls', 1, 3), ('Target', 1, 3), ('Chick-Fil-A', 1, 3), ('Whataburger', 1, 3);

-- Assigned to Trevor
INSERT INTO "order" (title, order_status_id, user_id)
VALUES ('Kroger', 1, 4), ('Shell', 1, 4), ('Exxon', 1, 4), ('Chevron', 1, 4);

INSERT INTO country (title) VALUES ('United States');

INSERT INTO state (abbreviation, title, country_id) VALUES
  ('AL', 'Alabama', 1),
  ('AK', 'Alaska', 1),
  ('AZ', 'Arizona', 1),
  ('AR', 'Arkansas', 1),
  ('CA', 'California', 1),
  ('CO', 'Colorado', 1),
  ('CT', 'Connecticut', 1),
  ('DE', 'Delaware', 1),
  ('DC', 'District of Columbia', 1),
  ('FL', 'Florida', 1),
  ('GA', 'Georgia', 1),
  ('HI', 'Hawaii', 1),
  ('ID', 'Idaho', 1),
  ('IL', 'Illinois', 1),
  ('IN', 'Indiana', 1),
  ('IA', 'Iowa', 1),
  ('KS', 'Kansas', 1),
  ('KY', 'Kentucky', 1),
  ('LA', 'Louisiana', 1),
  ('ME', 'Maine', 1),
  ('MD', 'Maryland', 1),
  ('MA', 'Massachusetts', 1),
  ('MI', 'Michigan', 1),
  ('MN', 'Minnesota', 1),
  ('MS', 'Mississippi', 1),
  ('MO', 'Missouri', 1),
  ('MT', 'Montana', 1),
  ('NE', 'Nebraska', 1),
  ('NV', 'Nevada', 1),
  ('NH', 'New Hampshire', 1),
  ('NJ', 'New Jersey', 1),
  ('NM', 'New Mexico', 1),
  ('NY', 'New York', 1),
  ('NC', 'North Carolina', 1),
  ('ND', 'North Dakota', 1),
  ('OH', 'Ohio', 1),
  ('OK', 'Oklahoma', 1),
  ('OR', 'Oregon', 1),
  ('PA', 'Pennsylvania', 1),
  ('PR', 'Puerto Rico', 1),
  ('RI', 'Rhode Island', 1),
  ('SC', 'South Carolina', 1),
  ('SD', 'South Dakota', 1),
  ('TN', 'Tennessee', 1),
  ('TX', 'Texas', 1),
  ('UT', 'Utah', 1),
  ('VT', 'Vermont', 1),
  ('VA', 'Virginia', 1),
  ('WA', 'Washington', 1),
  ('WV', 'West Virginia', 1),
  ('WI', 'Wisconsin', 1),
  ('WY', 'Wyoming', 1);

INSERT INTO order_note (title, message, order_id)
VALUES ('One', 'Testing One', 1), ('Two', 'Testing Two', 1),
  ('Three', 'Testing Three', 2), ('Four', 'Testing Four', 1),
  ('Five', 'Testing Five', 2);