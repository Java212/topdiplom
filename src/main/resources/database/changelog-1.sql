-- drops
DROP TABLE IF EXISTS public.users;
DROP SEQUENCE IF EXISTS users_id_seq;

DROP TABLE IF EXISTS public.budget_users;
DROP SEQUENCE IF EXISTS budget_id_seq;

DROP TABLE IF EXISTS public.expense_category;
DROP SEQUENCE IF EXISTS expense_category_id_seq;

DROP TABLE IF EXISTS public.users_expenses;
DROP SEQUENCE IF EXISTS users_expenses_id_seq;

DROP TABLE IF EXISTS public.incomes;
DROP SEQUENCE IF EXISTS incomes_id_seq;

DROP TABLE IF EXISTS public.users_incomes;
DROP SEQUENCE IF EXISTS users_incomes_id_seq;

--DROP TABLE IF EXISTS public.transactions_users;
--DROP SEQUENCE IF EXISTS transaction_id_seq;

-- creates

CREATE SEQUENCE users_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.users
(
    user_id integer NOT NULL DEFAULT nextval('users_id_seq'),
    user_name varchar NOT NULL,
    user_login varchar NOT NULL,
    user_password varchar NOT NULL DEFAULT "password",
    user_role varchar NOT NULL DEFAULT "USER",
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
);


CREATE SEQUENCE budget_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.budget_users
(
    budget_id integer NOT NULL DEFAULT nextval('budget_id_seq'),
    number_account_user varchar NOT NULL,
    current_balance_user integer NOT NULL ,
    CONSTRAINT budget_users_pkey PRIMARY KEY (budget_id)
);

CREATE SEQUENCE expense_category_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.expense_category
(
    expense_category_id integer NOT NULL DEFAULT nextval('expense_category_id_seq'),
    name_expense_category varchar NOT NULL,
    expense_amount integer NOT NULL, DEFAULT 0,
    date_expense varchar NOT NULL,
    CONSTRAINT expense_category_pkey PRIMARY KEY (expense_category_id)
);

CREATE SEQUENCE IF NOT EXISTS public.users_expenses_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.users_expenses
(
    user_expense_id integer NOT NULL DEFAULT nextval('users_expenses_id_seq'),
    user_id integer NOT NULL,
    expense_category_id integer NOT NULL,
    CONSTRAINT users_expenses_pkey PRIMARY KEY (user_expense_id),
    CONSTRAINT users_expenses_expenses_fkey
          FOREIGN KEY(expense_category_id)
    	  REFERENCES public.expense_category(expense_category_id),
    CONSTRAINT users_expenses_users_fkey
              FOREIGN KEY(user_id)
        	  REFERENCES public.users(user_id)
);

CREATE SEQUENCE incomes_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.incomes
(
    income_id integer NOT NULL DEFAULT nextval('incomes_id_seq'),
    source_income varchar NOT NULL,
    amount_income integer NOT NULL, DEFAULT 0,
    date_income varchar NOT NULL,
    CONSTRAINT incomes_pkey PRIMARY KEY (income_id)
);

CREATE SEQUENCE IF NOT EXISTS public.users_incomes_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.users_incomes
(
    user_income_id integer NOT NULL DEFAULT nextval('users_incomes_id_seq'),
    user_id integer NOT NULL,
    income_id integer NOT NULL,
    CONSTRAINT users_incomes_pkey PRIMARY KEY (user_income_id),
    CONSTRAINT users_incomes_incomes_fkey
          FOREIGN KEY(income_id)
    	  REFERENCES public.incomes(income_id),
    CONSTRAINT users_incomes_users_fkey
              FOREIGN KEY(user_id)
        	  REFERENCES public.users(user_id)
);