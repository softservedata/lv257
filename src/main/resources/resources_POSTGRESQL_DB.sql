CREATE TABLE privilege
(
  id BIGINT PRIMARY KEY NOT NULL,
  name VARCHAR(255)
);
CREATE TABLE role
(
  id BIGINT PRIMARY KEY NOT NULL,
  name VARCHAR(255)
);
CREATE TABLE roles_privileges
(
  role_id BIGINT NOT NULL,
  privilege_id BIGINT NOT NULL,
  CONSTRAINT fk_33w32xb8jfm3ocotvvecs3kuj FOREIGN KEY (role_id) REFERENCES role (id),
  CONSTRAINT fk_8e40d67gs32l8eerawr469awv FOREIGN KEY (privilege_id) REFERENCES privilege (id)
);
CREATE TABLE user_account
(
  id BIGINT PRIMARY KEY NOT NULL,
  enabled BOOLEAN NOT NULL,
  password VARCHAR(255) NOT NULL,
  secret VARCHAR(255),
  email VARCHAR(36) NOT NULL,
  role_id BIGINT,
  CONSTRAINT fk_fiywq2x4mg0ht0oq58ihdshpe FOREIGN KEY (role_id) REFERENCES role (id)
);