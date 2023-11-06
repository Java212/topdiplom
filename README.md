# topdiplom
TOP Academy graduation project

## "Домашняя бухгалтерия"

Позволяет вести доходы и расходы семьи

Пользователь по умолчанию (администратор): <br>
Логин: admin <br>
Пароль: admin

Для запуска приложения необходимо:
1. Создать базу данных в PostgreSQL с именем "finance". 
   В случае необходимости имя базы данных можно изменить в настройках
   (topdiplom/src/main/resources/application.properties - [ссылка](src/main/resources/application.properties))
2. Запустить класс `Main.java`<br>
   (topdiplom/src/main/java/ru.top.java212/Main.java - [ссылка](src/main/java/ru/top/java212/Main.java))
3. Перейти по ссылке: http://localhost:8080/
4. Для взаимодействия с программой необходимо зарегистрироваться/авторизоваться

Основные возможности:
1. Регистрация новых пользователей (http://localhost:8080/register)
2. Авторизация зарегистрированных пользователей (http://localhost:8080/login)
3. Добавлять и удалять категории доходов/расходов (http://localhost:8080/categories).
   Примечание: для удаления используемых категорий сначала нужно удалить позицию дохода/расхода с данной категорией
4. Добавлять/удалять доходы (http://localhost:8080/incomes)
5. Добавлять/удалять расходы (http://localhost:8080/expences)