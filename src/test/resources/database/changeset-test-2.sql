INSERT INTO expence_categories (name) VALUES ('Супермаркеты');
INSERT INTO income_categories (name) VALUES ('Зарплата');
INSERT INTO families (name) VALUES ('Ивановы');
INSERT INTO users (name, password, family_id) VALUES ('Иван', 'qwerty', 1);
INSERT INTO expences (name, summ, user_id, category_id, date) VALUES ('Подарки', 2244.5, 1, 1, '2023-08-11');
INSERT INTO incomes (name, summ, user_id, category_id, date) VALUES ('Подсчет за июль', 40432.6, 1, 1, '2023-08-10');

