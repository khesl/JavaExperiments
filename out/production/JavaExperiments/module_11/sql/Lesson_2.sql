-- select * from learn.employee;
-- select * from learn.department;
-- файл для заданий по MySql

-- руководители отделов
-- select * from learn.employee e, learn.department d where d.head_id = e.id;

-- 1. вывести ошибочно привязанных сотрудников, которые работают в одних отделах, а руководят другими;
-- select * from learn.employee e, learn.department d where e.department_id != d.id and d.head_id = e.id;

-- 2. вывести руководителей отделов, зарплата которых составляет менее 115 000 рублей в месяц;
-- select * from learn.employeevacation e, learn.department d where d.head_id = e.id and e.salary <= 115000;

-- 3. вывести руководителей отделов, которые вышли на работу до марта
select * from learn.employee e, learn.department d where d.head_id = e.id and extract(MONTH FROM e.hire_date) < 4;
