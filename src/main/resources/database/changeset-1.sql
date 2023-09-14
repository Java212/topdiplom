-- drops
DROP TABLE IF EXISTS public.pets;

DROP SEQUENCE IF EXISTS public.pets_id_seq;

-- creates

CREATE SEQUENCE public.pets_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.pets
(
    pet_id integer NOT NULL DEFAULT nextval('pets_id_seq'),
    name varchar NOT NULL,
    tag varchar ,
    CONSTRAINT pets_pkey PRIMARY KEY (pet_id),
    CONSTRAINT pets_name_unique UNIQUE(name)
);
