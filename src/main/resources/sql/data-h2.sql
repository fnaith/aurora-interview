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

INSERT INTO MENU (id, name, content, created_by, created_at, updated_by, updated_at, deleted)
VALUES (0, 'Menu 1', 'Content 1', 'manager@gmail.com', 1590224926931, 'manager@gmail.com', 1590224926931, 0);
INSERT INTO MENU (id, name, content, created_by, created_at, updated_by, updated_at, deleted)
VALUES (1, 'Menu 2', 'Content 2', 'manager@gmail.com', 1590224926932, 'manager@gmail.com', 1590224926932, 0);
INSERT INTO MENU (id, name, content, created_by, created_at, updated_by, updated_at, deleted)
VALUES (2, 'Menu 3', 'Content 3', 'manager@gmail.com', 1590224926933, 'manager@gmail.com', 1590224926933, 0);
INSERT INTO MENU (id, name, content, created_by, created_at, updated_by, updated_at, deleted)
VALUES (3, 'Menu 5', 'Content 5', 'user@gmail.com', 1590224926935, 'user@gmail.com', 1590224926935, 0);

CREATE SEQUENCE USER_SEQUENCE_ID START WITH (select max(id) + 1 from USER);
CREATE SEQUENCE ROLE_SEQUENCE_ID START WITH (select max(id) + 1 from ROLE);
