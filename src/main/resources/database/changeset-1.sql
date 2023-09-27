
-- drops


DROP TABLE IF EXISTS public.teachers;
DROP TABLE IF EXISTS public.subjects;

DROP SEQUENCE IF EXISTS teachers_id_seq;
DROP SEQUENCE IF EXISTS subjects_id_seq;

-- creates

CREATE SEQUENCE teachers_id_seq
    INCREMENT 1
    START WITH 1;


CREATE SEQUENCE subjects_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.teachers
(
    teacher_id integer NOT NULL DEFAULT nextval('teachers_id_seq'),
    teacher_fio varchar NOT NULL,
    CONSTRAINT teachers_pkey PRIMARY KEY (teacher_id)
);

CREATE TABLE IF NOT EXISTS public.subjects
(
    subject_id integer NOT NULL DEFAULT nextval('subjects_id_seq'),
    subject_name varchar NOT NULL,
    CONSTRAINT subjects_pkey PRIMARY KEY (subject_id),
       CONSTRAINT subjects_teachers_fkey
               FOREIGN KEY (teacher_id)
                 REFERENCES public.teachers(teacher_id)
                   ON DELETE CASCADE

);

