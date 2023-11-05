DROP TABLE IF EXISTS public.subjects;
DROP SEQUENCE IF EXISTS public.subjects_id_seq;

DROP TABLE IF EXISTS public.teachers;
DROP SEQUENCE IF EXISTS public.teachers_id_seq;


CREATE SEQUENCE IF NOT EXISTS public.subjects_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.subjects
(
    subject_id INTEGER NOT NULL DEFAULT nextval('subjects_id_seq'),
    title VARCHAR NOT NULL,
    teacher_id INTEGER,
    CONSTRAINT subjects_pkey PRIMARY KEY (subject_id)

);

CREATE SEQUENCE IF NOT EXISTS public.teachers_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.teachers
(
    teacher_id INTEGER NOT NULL DEFAULT nextval('teachers_id_seq'),
    name VARCHAR NOT NULL,
    surname VARCHAR NOT NULL,
    patronymic VARCHAR,
    CONSTRAINT teachers_pkey PRIMARY KEY (teacher_id)

);

ALTER TABLE IF EXISTS subjects
    ADD CONSTRAINT subjects_fk_teachers
        FOREIGN KEY (teacher_id) REFERENCES public.teachers(teacher_id);