-- Версия 4: Создание таблицы результатов опросов
CREATE TABLE survey_results (
    id SERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    survey_id BIGINT REFERENCES surveys(id),
    answer VARCHAR(255) NOT NULL
);