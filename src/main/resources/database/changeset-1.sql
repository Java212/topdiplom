-- drops
DROP TABLE IF EXISTS public.users;
DROP SEQUENCE IF EXISTS users_id_seq;

DROP TABLE IF EXISTS public.expenses;
DROP SEQUENCE IF EXISTS expenses_id_seq;

DROP TABLE IF EXISTS public.expenses_category;
DROP SEQUENCE IF EXISTS expenses_category_id_seq;

--DROP TABLE IF EXISTS public.users_expenses;
--DROP SEQUENCE IF EXISTS users_expenses_id_seq;

DROP TABLE IF EXISTS public.incomes;
DROP SEQUENCE IF EXISTS incomes_id_seq;

DROP TABLE IF EXISTS public.incomes_category;
DROP SEQUENCE IF EXISTS incomes_category_id_seq;


--DROP TABLE IF EXISTS public.users_incomes;
--DROP SEQUENCE IF EXISTS users_incomes_id_seq;


-- creates

CREATE SEQUENCE users_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.users
(
    user_id integer NOT NULL DEFAULT nextval('users_id_seq'),
    user_name varchar NOT NULL,
    user_login varchar NOT NULL,
    user_password varchar NOT NULL DEFAULT 'password',
    user_role varchar NOT NULL DEFAULT 'USER',
    user_starting_capital  numeric(9,2) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
);


CREATE SEQUENCE expenses_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.expenses
(
    expense_id integer NOT NULL DEFAULT nextval('expenses_id_seq'),
    expense_amount integer NOT NULL DEFAULT 0,
    date_expense varchar NOT NULL,
    CONSTRAINT expense_pkey PRIMARY KEY (expense_id)
);

--CREATE SEQUENCE IF NOT EXISTS public.users_expenses_id_seq
--    INCREMENT 1
--    START WITH 1;
--
--CREATE TABLE IF NOT EXISTS public.users_expenses
--(
--    user_expense_id integer NOT NULL DEFAULT nextval('users_expenses_id_seq'),
--    user_id integer NOT NULL,
--    expense_id integer NOT NULL,
--    CONSTRAINT users_expenses_pkey PRIMARY KEY (user_expense_id),
--    CONSTRAINT users_expenses_expenses_fkey
--          FOREIGN KEY(expense_id)
--    	  REFERENCES public.expense(expense_id),
--    CONSTRAINT users_expenses_users_fkey
--              FOREIGN KEY(user_id)
--        	  REFERENCES public.users(user_id)
--);

CREATE SEQUENCE expenses_category_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.expenses_category
(
    expense_category_id integer NOT NULL DEFAULT nextval('expenses_category_id_seq'),
    name_expense_category varchar NOT NULL,
    CONSTRAINT expenses_category_pkey PRIMARY KEY (expense_category_id)
);

CREATE SEQUENCE income_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.incomes
(
    income_id integer NOT NULL DEFAULT nextval('income_id_seq'),
    income_amount integer NOT NULL DEFAULT 0,
    date_income varchar NOT NULL,
    CONSTRAINT incomes_pkey PRIMARY KEY (income_id)
);

CREATE SEQUENCE incomes_category_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.incomes_category
(
    income_category_id integer NOT NULL DEFAULT nextval('incomes_category_id_seq'),
    source_income_category varchar NOT NULL,
    CONSTRAINT incomes_category_pkey PRIMARY KEY (income_category_id)
);



--CREATE SEQUENCE IF NOT EXISTS public.users_incomes_id_seq
--    INCREMENT 1
--    START WITH 1;
--
--CREATE TABLE IF NOT EXISTS public.users_incomes
--(
--    user_income_id integer NOT NULL DEFAULT nextval('users_incomes_id_seq'),
--    user_id integer NOT NULL,
--    income_id integer NOT NULL,
--    CONSTRAINT users_incomes_pkey PRIMARY KEY (user_income_id),
--    CONSTRAINT users_incomes_incomes_fkey
--          FOREIGN KEY(income_id)
--    	  REFERENCES public.incomes(income_id),
--    CONSTRAINT users_incomes_users_fkey
--              FOREIGN KEY(user_id)
--        	  REFERENCES public.users(user_id)
--);

insert into users (user_name, user_login,user_starting_capital ) values ('Bob', 'df34', 1000.52);
insert into expenses_category (name_expense_category) values ('квартплата');