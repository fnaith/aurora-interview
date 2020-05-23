CREATE TABLE ROLE (id bigint not null, name varchar(255), primary key (id));
CREATE TABLE USER (id bigint not null, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), primary key (id));
CREATE TABLE USERS_ROLES (user_id bigint not null, role_id bigint not null);

CREATE TABLE MENU (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  content VARCHAR NOT NULL,
  created_by VARCHAR(50) NOT NULL,
  created_at BIGINT NOT NULL,
  updated_by VARCHAR(50) NOT NULL,
  updated_at BIGINT NOT NULL,
  deleted NUMBER(1,0) NOT NULL,
  PRIMARY KEY(id)
);
