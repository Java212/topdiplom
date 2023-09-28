-- drops
DROP TABLE IF EXISTS public.subjects;

DROP SEQUENCE IF EXISTS public.subjects_id_seq;

-- creates

CREATE SEQUENCE public.subjects_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.subjects
(
    subject_id integer NOT NULL DEFAULT nextval('subjects_id_seq'),
    subject_name varchar NOT NULL,
    CONSTRAINT subjects_pkey PRIMARY KEY (subject_id)
);

insert into subjects(subject_name) values ('Русский язык');
insert into subjects(subject_name) values ('Литература');
insert into subjects(subject_name) values ('Алгебра');
insert into subjects(subject_name) values ('Геометрия');
insert into subjects(subject_name) values ('Физика');
insert into subjects(subject_name) values ('Химия');
insert into subjects(subject_name) values ('Физкультура');
insert into subjects(subject_name) values ('Труд');
insert into subjects(subject_name) values ('Рисование');



