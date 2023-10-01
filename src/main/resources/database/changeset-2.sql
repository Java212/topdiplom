-- DROPS

DROP TABLE IF EXISTS public.teachers;

DROP SEQUENCE IF EXISTS public.teachers_id_seq;

-- CREATES

CREATE SEQUENCE public.teachers_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.teachers
(
    id integer NOT NULL DEFAULT nextval('teachers_id_seq'),
    name varchar NOT NULL,
    CONSTRAINT teachers_pkey PRIMARY KEY (id)
    );

-- ADDS

INSERT INTO teachers(name) VALUES ('Иванов А.Б.');
INSERT INTO teachers(name) VALUES ('Петров В.Г.');
INSERT INTO teachers(name) VALUES ('Сидоров Д.Е.');

ALTER TABLE subjects
ADD teacher_id integer;

ALTER TABLE subjects
ADD CONSTRAINT teachers_fkey FOREIGN KEY (teacher_id)
REFERENCES teachers(id);

UPDATE subjects SET teacher_id = 1 WHERE id = 1 OR id = 2;
UPDATE subjects SET teacher_id = 2 WHERE id = 3 OR id = 4;
UPDATE subjects SET teacher_id = 3 WHERE id = 8 OR id = 9;





