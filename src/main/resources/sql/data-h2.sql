INSERT INTO USER (email, first_name, last_name, password, id)
VALUES ('manager@gmail.com', 'manager', 'manager', '$2a$10$kmm0bvEkSRQdfyYDL3Y7vuVd8qbt3Y86sQyM3giOzWXls5J3ebYKe', 1);
INSERT INTO ROLE (name, id)
VALUES ('USER', 2);
INSERT INTO ROLE (name, id)
VALUES ('MANAGER', 3);
INSERT INTO USERS_ROLES (user_id, role_id)
VALUES (1, 2);
INSERT INTO USERS_ROLES (user_id, role_id)
VALUES (1, 3);
INSERT INTO USER (email, first_name, last_name, password, id)
VALUES ('user@gmail.com', 'user', 'user', '$2a$10$.wbpsQz6y1OHZ0wCCInQX.x9gLrrn5/D1k01VF2Pt9E5kygmIeKca', 4);
INSERT INTO ROLE (name, id)
VALUES ('USER', 5);
INSERT INTO USERS_ROLES (user_id, role_id)
VALUES (4, 5);

CREATE SEQUENCE USER_SEQUENCE_ID START WITH (select max(id) + 1 from USER);
CREATE SEQUENCE ROLE_SEQUENCE_ID START WITH (select max(id) + 1 from ROLE);
