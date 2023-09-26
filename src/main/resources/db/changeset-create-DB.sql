-- drops

DROP  TABLE IF EXISTS public.teachers CASCADE;
DROP SEQUENCE IF EXISTS public.teachers_id_seq;
DROP TABLE IF EXISTS public.subjects;
DROP SEQUENCE IF EXISTS public.subjects_id_seq;



-- creates

CREATE SEQUENCE public.teachers_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.teachers
(
    teacher_id integer NOT NULL DEFAULT nextval('teachers_id_seq'),
    name varchar NOT NULL,
    CONSTRAINT teachers_pkey PRIMARY KEY (teacher_id)
);
CREATE SEQUENCE public.subjects_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.subjects
(
    subject_id integer NOT NULL DEFAULT nextval('subjects_id_seq'),
    name varchar NOT NULL,
	teacher_id integer,
    CONSTRAINT subjects_pkey PRIMARY KEY (subject_id),
	CONSTRAINT subjects_teachers_fkey
            FOREIGN KEY(teacher_id)
               REFERENCES public.teachers(teacher_id)
                 ON DELETE CASCADE
);
