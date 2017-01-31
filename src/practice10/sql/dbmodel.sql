use practice10;

CREATE TABLE roles
(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30) NOT NULL
);
DESC roles;

CREATE TABLE users
(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(30) NOT NULL UNIQUE,
  password VARCHAR(30) NOT NULL,
  name VARCHAR(50),
  role_id INT NOT NULL,
  CONSTRAINT roles_role_id_fk
  FOREIGN KEY (role_id)
  REFERENCES roles(id)
);
DESC users;

INSERT INTO roles (name) VALUE ('admin');
INSERT INTO roles (name) VALUE  ('client');

INSERT INTO users (login, password, name, role_id) VALUE ('krich', 'pass', 'nikita', 1);
INSERT INTO users (login, password, name, role_id) VALUE ('user', '1234', 'vasya', 2);
INSERT INTO users (login, password, name, role_id) VALUE ('luna', '1234', 'Женя', 2);

SELECT users.id, users.login, users.password,users.name, roles.name
FROM users
  INNER JOIN roles
WHERE users.role_id = roles.id
      AND users.login = 'admin';

SELECT * FROM users WHERE login = 'user' AND password = '1234';

UPDATE users SET name = 'Никита' WHERE login = 'krich';

/*удаление всех записей таблицы*/
TRUNCATE TABLE users;
TRUNCATE TABLE roles;
DROP TABLE users;
DROP TABLE roles;
