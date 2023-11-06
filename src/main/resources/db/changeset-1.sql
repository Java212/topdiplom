
DROP TABLE IF EXISTS public.orders;
DROP TABLE IF EXISTS public.products;
DROP TABLE IF EXISTS public.profiles;
DROP TABLE IF EXISTS public.categories;
DROP TABLE IF EXISTS public.users_roles;
DROP TABLE IF EXISTS public.roles;
DROP TABLE IF EXISTS public.users;
DROP TABLE IF EXISTS public.addresses;

DROP SEQUENCE IF EXISTS public.orders_id_seq;
DROP SEQUENCE IF EXISTS public.products_id_seq;
DROP SEQUENCE IF EXISTS public.profiles_id_seq;
DROP SEQUENCE IF EXISTS public.categories_id_seq;
DROP SEQUENCE IF EXISTS public.users_roles_id_seq;
DROP SEQUENCE IF EXISTS public.roles_id_seq;
DROP SEQUENCE IF EXISTS public.users_id_seq;
DROP SEQUENCE IF EXISTS public.addresses_id_seq;


CREATE SEQUENCE IF NOT EXISTS public.addresses_id_seq
    INCREMENT 1
    START WITH 1;

CREATE SEQUENCE IF NOT EXISTS public.users_id_seq
    INCREMENT 1
    START WITH 1;

CREATE SEQUENCE IF NOT EXISTS public.roles_id_seq
    INCREMENT 1
    START WITH 1;

CREATE SEQUENCE IF NOT EXISTS public.users_roles_id_seq
    INCREMENT 1
    START WITH 1;

CREATE SEQUENCE IF NOT EXISTS public.categories_id_seq
    INCREMENT 1
    START WITH 1;

CREATE SEQUENCE IF NOT EXISTS public.profiles_id_seq
    INCREMENT 1
    START WITH 1;

CREATE SEQUENCE IF NOT EXISTS public.products_id_seq
    INCREMENT 1
    START WITH 1;

CREATE SEQUENCE IF NOT EXISTS public.orders_id_seq
    INCREMENT 1
    START WITH 1;

-- Создаем таблицу адресов
CREATE TABLE IF NOT EXISTS public.addresses
(
    address_id INTEGER NOT NULL DEFAULT nextval('addresses_id_seq'),
    district VARCHAR NOT NULL,
    street VARCHAR NOT NULL,
    number_of_house INTEGER NOT NULL,
    apartment_number INTEGER,
    CONSTRAINT addresses_pkey PRIMARY KEY (address_id)
);

-- Создаем таблицу пользователей
CREATE TABLE IF NOT EXISTS public.users
(
    user_id INTEGER NOT NULL DEFAULT nextval('users_id_seq'),
    user_name VARCHAR NOT NULL,
    password VARCHAR NOT NULL DEFAULT 'password',
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
);

-- Создаем таблицу ролей
CREATE TABLE IF NOT EXISTS public.roles
(
    role_id INTEGER NOT NULL DEFAULT nextval('roles_id_seq'),
    name VARCHAR NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (role_id)
);

-- Создаем таблицу связи пользователей и ролей
CREATE TABLE IF NOT EXISTS public.users_roles
(
    user_role_id INTEGER NOT NULL DEFAULT nextval('users_roles_id_seq'),
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    CONSTRAINT users_roles_pkey PRIMARY KEY (user_role_id),
    CONSTRAINT users_roles_roles_fkey
          FOREIGN KEY(role_id)
          REFERENCES public.roles(role_id),
    CONSTRAINT users_roles_users_fkey
              FOREIGN KEY(user_id)
              REFERENCES public.users(user_id)
);

-- Создаем таблицу категорий
CREATE TABLE IF NOT EXISTS public.categories
(
    category_id INTEGER NOT NULL DEFAULT nextval('categories_id_seq'),
    title VARCHAR NOT NULL,
    CONSTRAINT categories_pkey PRIMARY KEY (category_id)
);

-- Создаем таблицу информации о пользователях
CREATE TABLE IF NOT EXISTS public.profiles
(
     profile_id INTEGER NOT NULL DEFAULT nextval('profiles_id_seq'),
     name VARCHAR,
     email VARCHAR,
     phone_number VARCHAR,
     user_id INTEGER NOT NULL,
     address_id INTEGER,
     CONSTRAINT profiles_pkey PRIMARY KEY (profile_id),
     CONSTRAINT profiles_fk_users
         FOREIGN KEY (user_id) REFERENCES public.users(user_id),
     CONSTRAINT profiles_id_fk_address
         FOREIGN KEY (address_id) REFERENCES public.addresses(address_id)
);

-- Создаем таблицу продуктов
CREATE TABLE IF NOT EXISTS public.products
(
    product_id INTEGER NOT NULL DEFAULT nextval('products_id_seq'),
    category_id INTEGER NOT NULL,
    profile_id INTEGER NOT NULL,
    title VARCHAR,
    link_to_the_image VARCHAR,
    specification VARCHAR,
    price NUMERIC(19,2),
    is_busy BOOLEAN NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (product_id),
    CONSTRAINT products_fk_categories
        FOREIGN KEY (category_id) REFERENCES public.categories(category_id),
    CONSTRAINT products_fk_profiles
        FOREIGN KEY (profile_id) REFERENCES public.profiles(profile_id)
);

-- Создаем таблицу заказов
CREATE TABLE IF NOT EXISTS public.orders
(
    order_id INTEGER NOT NULL DEFAULT nextval('orders_id_seq'),
    product_id INTEGER NOT NULL,
    profile_id INTEGER NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (order_id),
    CONSTRAINT orders_fk_profiles
        FOREIGN KEY (profile_id) REFERENCES public.profiles(profile_id),
    CONSTRAINT orders_fk_products
        FOREIGN KEY (product_id) REFERENCES public.products(product_id)
);

