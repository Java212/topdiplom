-- DROPS

DROP TABLE IF EXISTS public.subjects;

DROP SEQUENCE IF EXISTS public.subjects_id_seq;

-- CREATES

CREATE SEQUENCE public.subjects_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.subjects
(
    id integer NOT NULL DEFAULT nextval('subjects_id_seq'),
    name varchar NOT NULL,
    CONSTRAINT subjects_pkey PRIMARY KEY (id)
    );

-- ADDS

INSERT INTO subjects(name) VALUES ('Русский язык');
INSERT INTO subjects(name) VALUES ('Литература');
INSERT INTO subjects(name) VALUES ('Алгебра');
INSERT INTO subjects(name) VALUES ('Геометрия');
INSERT INTO subjects(name) VALUES ('Физика');
INSERT INTO subjects(name) VALUES ('Химия');
INSERT INTO subjects(name) VALUES ('Физкультура');
INSERT INTO subjects(name) VALUES ('Труд');
INSERT INTO subjects(name) VALUES ('Рисование');



