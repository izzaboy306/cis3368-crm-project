INSERT INTO order_status (title) VALUES ('Prospect');
INSERT INTO order_status (title) VALUES ('Information Gathering');
INSERT INTO order_status (title) VALUES ('Proposal');
INSERT INTO order_status (title) VALUES ('Negotiating');
INSERT INTO order_status (title) VALUES ('Closing');

INSERT INTO user_type (title) VALUES ('User');
INSERT INTO user_type (title) VALUES ('Manager');

INSERT INTO "user" (first_name, last_name, user_type_id) VALUES ('Ethan', 'Orcutt', 2);
INSERT INTO "user" (first_name, last_name, user_type_id) VALUES ('Clay', 'Orcutt', 1);
INSERT INTO "user" (first_name, last_name, user_type_id) VALUES ('Tony', 'Orcutt', 1);
INSERT INTO "user" (first_name, last_name, user_type_id) VALUES ('Trevor', 'Orcutt', 1);

-- Assigned to Clay
INSERT INTO "order" (title, order_status_id, user_id) VALUES ('Home Depot', 1, 2);
INSERT INTO "order" (title, order_status_id, user_id) VALUES ('Home Depot', 1, 2);
INSERT INTO "order" (title, order_status_id, user_id) VALUES ('Home Depot', 1, 2);
INSERT INTO "order" (title, order_status_id, user_id) VALUES ('Home Depot', 1, 2);

-- Assigned to Tony
INSERT INTO "order" (title, order_status_id, user_id) VALUES ('Home Depot', 1, 3);
INSERT INTO "order" (title, order_status_id, user_id) VALUES ('Home Depot', 1, 3);
INSERT INTO "order" (title, order_status_id, user_id) VALUES ('Home Depot', 1, 3);
INSERT INTO "order" (title, order_status_id, user_id) VALUES ('Home Depot', 1, 3);

-- Assigned to Trevor
INSERT INTO "order" (title, order_status_id, user_id) VALUES ('Home Depot', 1, 4);
INSERT INTO "order" (title, order_status_id, user_id) VALUES ('Home Depot', 1, 4);
INSERT INTO "order" (title, order_status_id, user_id) VALUES ('Home Depot', 1, 4);
INSERT INTO "order" (title, order_status_id, user_id) VALUES ('Home Depot', 1, 4);