INSERT INTO expence_categories (name) VALUES ('Супермаркеты');
INSERT INTO expence_categories (name) VALUES ('Транспорт');
INSERT INTO expence_categories (name) VALUES ('Кино и развлечения');
INSERT INTO expence_categories (name) VALUES ('Одежда');
INSERT INTO expence_categories (name) VALUES ('Образование');
INSERT INTO expence_categories (name) VALUES ('Онлайн-магазины');
INSERT INTO expence_categories (name) VALUES ('Погашение кредитов');
INSERT INTO expence_categories (name) VALUES ('Прочее');

INSERT INTO income_categories (name) VALUES ('Зарплата');
INSERT INTO income_categories (name) VALUES ('Премия');
INSERT INTO income_categories (name) VALUES ('Продажа вещей');
INSERT INTO income_categories (name) VALUES ('Возврат долга');
INSERT INTO income_categories (name) VALUES ('Возврат налоговых вычетов');
INSERT INTO income_categories (name) VALUES ('Инвестиции');
INSERT INTO income_categories (name) VALUES ('Подарок');
INSERT INTO income_categories (name) VALUES ('Проценты по вкладам');

INSERT INTO expences (name, summ, user_id, category_id, date) VALUES ('Покупка еды', 2500, 1, 1, '2023-09-14');
INSERT INTO expences (name, summ, user_id, category_id, date) VALUES ('Заправка машины', 1000, 1, 2, '2023-09-20');
INSERT INTO expences (name, summ, user_id, category_id, date) VALUES ('Еда кошке', 900, 1, 1, '2023-09-29');
INSERT INTO expences (name, summ, user_id, category_id, date) VALUES ('Подарки', 5000, 1, 1, '2023-09-30');

INSERT INTO incomes (name, summ, user_id, category_id, date) VALUES ('Аванс за август', 20432.6, 1, 1, '2023-08-25');
INSERT INTO incomes (name, summ, user_id, category_id, date) VALUES ('Подсчет за август', 34323.3, 1, 1, '2023-09-10');
INSERT INTO incomes (name, summ, user_id, category_id, date) VALUES ('Аванс за сентябрь', 10321.7, 1, 1, '2023-09-26');
INSERT INTO incomes (name, summ, user_id, category_id, date) VALUES ('Подсчет за сентябрь', 40432.6, 1, 1, '2023-10-10');