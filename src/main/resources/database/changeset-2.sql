insert into users (user_name, user_login,user_starting_capital ) values ('Bob', 'df34', 1000.52);

insert into users (user_name, user_login, user_password, user_role, user_starting_capital ) values ('John', 'sa', '123', 'ADMIN', 0);

insert into expenses_category (name_expense_category) values ('квартплата');
insert into incomes_category (source_income_category) values ('премия');

insert into expenses (expense_amount, user_id, expense_category_id) values (1000, 1, 1);
insert into incomes (income_amount, user_id, income_category_id) values (35000, 1, 1);
