DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

-- password
INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');
-- admin
INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES ('ROLE_USER', 100000);
INSERT INTO user_roles (role, user_id) VALUES ('ROLE_ADMIN', 100001);

ALTER SEQUENCE meals_id_seq RESTART WITH 1;
-- for meals
INSERT INTO meals (datetime, description, calories, user_id) VALUES ('2015-05-30T10:00', 'Завтрак', 500, 100000);
INSERT INTO meals (datetime, description, calories, user_id) VALUES ('2015-05-30T13:00', 'Обед', 1000, 100000);
INSERT INTO meals (datetime, description, calories, user_id) VALUES ('2015-05-30T20:00', 'Ужин', 500, 100000);
INSERT INTO meals (datetime, description, calories, user_id) VALUES ('2015-05-31T10:00', 'Завтрак', 500, 100000);
INSERT INTO meals (datetime, description, calories, user_id) VALUES ('2015-05-31T13:00', 'Обед', 1000, 100000);
INSERT INTO meals (datetime, description, calories, user_id) VALUES ('2015-05-31T20:00', 'Ужин', 520, 100000);

INSERT INTO meals (datetime, description, calories, user_id) VALUES ('2015-06-01T14:00', 'Админ ланч', 510, 100001);
INSERT INTO meals (datetime, description, calories, user_id) VALUES ('2015-06-01T21:00', 'Админ ужин', 510, 100001);
