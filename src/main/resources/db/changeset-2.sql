-- drops

DROP TABLE IF EXISTS public.tools CASCADE;

DROP SEQUENCE IF EXISTS public.tools_id_seq;

DROP TABLE IF EXISTS public.orders CASCADE ;

DROP SEQUENCE IF EXISTS public.orders_id_seq;

DROP TABLE IF EXISTS public.addresses CASCADE;

DROP SEQUENCE IF EXISTS public.addresses_id_seq;

DROP TABLE IF EXISTS public.periods;

DROP SEQUENCE IF EXISTS public.periods_id_seq;

-- creates
CREATE SEQUENCE public.addresses_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.addresses
(
    address_id integer NOT NULL DEFAULT nextval('addresses_id_seq'),
    district varchar NOT NULL,
    street varchar NOT NULL,
    CONSTRAINT addresses_pkey PRIMARY KEY (address_id)
);

CREATE SEQUENCE public.periods_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.periods
(
    period_id integer NOT NULL DEFAULT nextval('periods_id_seq'),
    start_date timestamp without time zone,
	stop_date timestamp without time zone,
    CONSTRAINT periods_pkey PRIMARY KEY (period_id)
);


CREATE SEQUENCE public.tools_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.tools
(
    tool_id integer NOT NULL DEFAULT nextval('tools_id_seq'),
    tool_name varchar NOT NULL,
    prise real NOT NULL DEFAULT 0.0,
	address_id integer NOT NULL,
	in_rent boolean NOT NULL,
    CONSTRAINT tools_pkey PRIMARY KEY (tool_id),
	CONSTRAINT tools_address_fkey
          FOREIGN KEY(address_id)
    	  REFERENCES public.addresses(address_id)
    	  ON DELETE CASCADE
);

CREATE SEQUENCE public.orders_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.orders
(
    order_id integer NOT NULL DEFAULT nextval('orders_id_seq'),
    order_name varchar NOT NULL,
    user_id integer NOT NULL,
	tool_id integer NOT NULL,
	period_id integer NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (order_id),
	 CONSTRAINT orders_users_fkey
          FOREIGN KEY(user_id)
    	  REFERENCES public.users(user_id)
    	  ON DELETE CASCADE,
	 CONSTRAINT orders_tools_fkey
          FOREIGN KEY(tool_id)
    	  REFERENCES public.tools(tool_id)
    	  ON DELETE CASCADE,
	 CONSTRAINT orders_periods_fkey
          FOREIGN KEY(period_id)
    	  REFERENCES public.periods(period_id)
    	  ON DELETE CASCADE
);

