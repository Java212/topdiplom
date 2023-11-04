-- DROPS

DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS roles;

DROP SEQUENCE IF EXISTS users_roles_id_seq;
DROP SEQUENCE IF EXISTS roles_id_seq;

-- CREATES

CREATE SEQUENCE IF NOT EXISTS roles_id_seq;
CREATE SEQUENCE IF NOT EXISTS users_roles_id_seq;

CREATE TABLE IF NOT EXISTS roles
(
    id integer NOT NULL DEFAULT nextval('roles_id_seq'),
    name varchar NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_role_id integer NOT NULL DEFAULT nextval('users_roles_id_seq'),
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    CONSTRAINT users_roles_pk PRIMARY KEY (user_role_id),
    CONSTRAINT users_roles_roles_fk FOREIGN KEY(role_id) REFERENCES roles(id),
    CONSTRAINT users_roles_users_fk FOREIGN KEY(user_id) REFERENCES users(id)
);

-- ADDS

-- System administrator:
-- Login: admin
-- Password: admin

INSERT INTO users (name, password) VALUES ('admin', '$2y$10$KZEMVudPau3G3i2BZ8V3uOelpfsSwDEvadhJUlpTjUAhB5DCG0go2');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO users_roles (user_id,role_id) VALUES (1,1);
INSERT INTO users_roles (user_id,role_id) VALUES (1,2);

-- COMMENTS

COMMENT ON TABLE roles IS 'Роли пользователей';
COMMENT ON COLUMN roles.id IS 'Идентификатор';
COMMENT ON COLUMN roles.name IS 'Наименование';

COMMENT ON TABLE users_roles IS 'Промежуточная таблица для связи пользователей и ролей';
COMMENT ON COLUMN users_roles.user_id IS 'Идентификатор пользователя';
COMMENT ON COLUMN users_roles.role_id IS 'Идентификатор роли';