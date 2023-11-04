-- DROPS

DROP TABLE IF EXISTS expences;
DROP TABLE IF EXISTS incomes;
DROP TABLE IF EXISTS expence_categories;
DROP TABLE IF EXISTS income_categories;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS families;

DROP SEQUENCE IF EXISTS expences_id_seq;
DROP SEQUENCE IF EXISTS incomes_id_seq;
DROP SEQUENCE IF EXISTS expence_categories_id_seq;
DROP SEQUENCE IF EXISTS income_categories_id_seq;
DROP SEQUENCE IF EXISTS users_id_seq;
DROP SEQUENCE IF EXISTS families_id_seq;

-- CREATES

CREATE SEQUENCE expences_id_seq;
CREATE SEQUENCE incomes_id_seq;
CREATE SEQUENCE expence_categories_id_seq;
CREATE SEQUENCE income_categories_id_seq;
CREATE SEQUENCE users_id_seq;
CREATE SEQUENCE families_id_seq;

CREATE TABLE IF NOT EXISTS expence_categories
(
    id integer NOT NULL DEFAULT nextval('expence_categories_id_seq'),
    name varchar NOT NULL,
    CONSTRAINT expence_categories_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS income_categories
(
    id integer NOT NULL DEFAULT nextval('income_categories_id_seq'),
    name varchar NOT NULL,
    CONSTRAINT income_categories_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS families
(
    id integer NOT NULL DEFAULT nextval('families_id_seq'),
    name varchar NOT NULL,
    CONSTRAINT families_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users
(
    id integer NOT NULL DEFAULT nextval('users_id_seq'),
    name varchar NOT NULL,
    password varchar NOT NULL,
    family_id integer NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (id),
    CONSTRAINT users_families_fk FOREIGN KEY (family_id) REFERENCES families(id)
);

CREATE TABLE IF NOT EXISTS expences
(
    id integer NOT NULL DEFAULT nextval('expences_id_seq'),
    name varchar NOT NULL,
    summ double precision NOT NULL,
    user_id integer NOT NULL,
    category_id integer NOT NULL,
    date date NOT NULL,
    CONSTRAINT expences_pk PRIMARY KEY (id),
    CONSTRAINT expences_users_fk FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT expences_categories_fk FOREIGN KEY (category_id) REFERENCES expence_categories(id)
);

CREATE TABLE IF NOT EXISTS incomes
(
    id integer NOT NULL DEFAULT nextval('incomes_id_seq'),
    name varchar NOT NULL,
    summ double precision NOT NULL,
    user_id integer NOT NULL,
    category_id integer NOT NULL,
    date date NOT NULL,
    CONSTRAINT incomes_pk PRIMARY KEY (id),
    CONSTRAINT incomes_users_fk FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT incomes_categories_fk FOREIGN KEY (category_id) REFERENCES income_categories(id)
);

-- COMMENTS

COMMENT ON TABLE expences IS 'Расходы';
COMMENT ON COLUMN expences.id IS 'Идентификатор';
COMMENT ON COLUMN expences.name IS 'Наименование';
COMMENT ON COLUMN expences.summ IS 'Сумма';
COMMENT ON COLUMN expences.user_id IS 'Идентификатор пользователя';
COMMENT ON COLUMN expences.category_id IS 'Идентификатор категории';
COMMENT ON COLUMN expences.date IS 'Дата';

COMMENT ON TABLE incomes IS 'Доходы';
COMMENT ON COLUMN incomes.id IS 'Идентификатор';
COMMENT ON COLUMN incomes.name IS 'Наименование';
COMMENT ON COLUMN incomes.summ IS 'Сумма';
COMMENT ON COLUMN incomes.user_id IS 'Идентификатор пользователя';
COMMENT ON COLUMN incomes.category_id IS 'Идентификатор категории';
COMMENT ON COLUMN incomes.date IS 'Дата';

COMMENT ON TABLE expence_categories IS 'Категории расходов';
COMMENT ON COLUMN expence_categories.id IS 'Идентификатор';
COMMENT ON COLUMN expence_categories.name IS 'Наименование';

COMMENT ON TABLE income_categories IS 'Категории доходов';
COMMENT ON COLUMN income_categories.id IS 'Идентификатор';
COMMENT ON COLUMN income_categories.name IS 'Наименование';

COMMENT ON TABLE users IS 'Пользователи';
COMMENT ON COLUMN users.id IS 'Идентификатор';
COMMENT ON COLUMN users.name IS 'Имя пользователя';
COMMENT ON COLUMN users.password IS 'Пароль (шифр.)';
COMMENT ON COLUMN users.family_id IS 'Идентификатор семьи';

COMMENT ON TABLE families IS 'Семьи';
COMMENT ON COLUMN families.id IS 'Идентификатор';
COMMENT ON COLUMN families.name IS 'Наименование';
