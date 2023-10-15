DROP TABLE IF EXISTS public.users_roles;

DROP SEQUENCE IF EXISTS public.users_roles_id_seq;

DROP TABLE IF EXISTS public.users;

DROP SEQUENCE IF EXISTS public.users_id_seq;

DROP TABLE IF EXISTS public.roles;

DROP SEQUENCE IF EXISTS public.roles_id_seq;

DROP TABLE IF EXISTS public.addresses;

DROP SEQUENCE IF EXISTS public.addresses_id_seq;

DROP TABLE IF EXISTS public.categories;

DROP SEQUENCE IF EXISTS public.categories_id_seq;

DROP TABLE IF EXISTS public.users_info;

DROP SEQUENCE IF EXISTS public.users_info_id_seq;

DROP TABLE IF EXISTS public.orders;

DROP SEQUENCE IF EXISTS public.orders_id_seq;

DROP TABLE IF EXISTS public.products;

DROP SEQUENCE IF EXISTS public.products_id_seq;




-- creates
CREATE SEQUENCE IF NOT EXISTS public.addresses_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.addresses
(
    address_id INTEGER NOT NULL DEFAULT nextval('addresses_id_seq'),
    district VARCHAR NOT NULL,
    street VARCHAR NOT NULL,
    number_of_house INTEGER NOT NULL,
    apartment_number INTEGER,
    CONSTRAINT addresses_pkey PRIMARY KEY (address_id)

);

CREATE SEQUENCE public.users_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.users
(
    user_id integer NOT NULL DEFAULT nextval('users_id_seq'),
    user_name varchar NOT NULL,
    password varchar NOT NULL DEFAULT 'password',
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
);

CREATE SEQUENCE IF NOT EXISTS public.roles_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.roles
(
    role_id integer NOT NULL DEFAULT nextval('roles_id_seq'),
    name varchar NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (role_id)
);

CREATE SEQUENCE IF NOT EXISTS public.users_roles_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.users_roles
(
    user_role_id integer NOT NULL DEFAULT nextval('users_roles_id_seq'),
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    CONSTRAINT users_roles_pkey PRIMARY KEY (user_role_id),
    CONSTRAINT users_roles_roles_fkey
          FOREIGN KEY(role_id)
    	  REFERENCES public.roles(role_id),
    CONSTRAINT users_roles_users_fkey
              FOREIGN KEY(user_id)
        	  REFERENCES public.users(user_id)
);



CREATE SEQUENCE IF NOT EXISTS public.categories_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.categories
(
    category_id INTEGER NOT NULL DEFAULT nextval('categories_id_seq'),
    title VARCHAR NOT NULL,
    CONSTRAINT categories_pkey PRIMARY KEY (category_id)

);


CREATE SEQUENCE IF NOT EXISTS public.users_info_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.users_info
(
     user_info_id INTEGER NOT NULL DEFAULT nextval('users_info_id_seq'),
     name varchar,
     email varchar,
     phone_number varchar,
     user_id INTEGER NOT NULL,
     address_id INTEGER,
     CONSTRAINT users_info_pkey PRIMARY KEY (user_info_id)

);



CREATE SEQUENCE IF NOT EXISTS public.products_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.products
(
    product_id INTEGER NOT NULL DEFAULT nextval('products_id_seq'),
    category_id INTEGER NOT NULL,
    user_info_id INTEGER NOT NULL,
    title VARCHAR,
    link_to_the_image varchar,
    specification varchar,
    price NUMERIC(19,2),
    is_busy BOOLEAN NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (product_id)

);

CREATE SEQUENCE IF NOT EXISTS public.orders_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.orders
(
    order_id INTEGER NOT NULL DEFAULT nextval('orders_id_seq'),
    product_id INTEGER NOT NULL,
    user_info_id INTEGER NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (order_id)

);

ALTER TABLE IF EXISTS products
    ADD CONSTRAINT products_fk_categories
        FOREIGN KEY (category_id) REFERENCES public.categories(category_id);

ALTER TABLE IF EXISTS products
         ADD CONSTRAINT products_fk_users_info
                FOREIGN KEY (user_info_id) REFERENCES public.users_info(user_info_id);



ALTER TABLE IF EXISTS users_info
    ADD CONSTRAINT users_info_fk_users
        FOREIGN KEY (user_id) REFERENCES public.users(user_id);

ALTER TABLE IF EXISTS users_info
    ADD CONSTRAINT users_info_fk_address
        FOREIGN KEY (address_id) REFERENCES public.addresses(address_id);



ALTER TABLE IF EXISTS orders
    ADD CONSTRAINT orders_fk_users_info
        FOREIGN KEY (user_info_id) REFERENCES public.users_info(user_info_id);

ALTER TABLE IF EXISTS orders
    ADD CONSTRAINT orders_fk_products
        FOREIGN KEY (product_id) REFERENCES public.products(product_id);


