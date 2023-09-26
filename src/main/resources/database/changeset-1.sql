
-- drops

DROP TABLE IF EXISTS public.subjects;
DROP TABLE IF EXISTS public.teachers;

-- creates


CREATE TABLE IF NOT EXISTS public.subjects
(
    subject_id integer NOT NULL DEFAULT nextval('users_id_seq'),
    subject_name varchar NOT NULL,
    CONSTRAINT subjects_pkey PRIMARY KEY (subject_id)
);

CREATE TABLE IF NOT EXISTS public.teachers
(
    teacher_id integer NOT NULL DEFAULT nextval('teachers_id_seq'),
    teacher_fio varchar NOT NULL,
    CONSTRAINT teachers_pkey PRIMARY KEY (teacher_id)
        CONSTRAINT teachers_subjects_fkey
            FOREIGN KEY (subject_id)
                 REFERENCES public.subjects(subject_id)
                                 ON DELETE CASCADE,
);

