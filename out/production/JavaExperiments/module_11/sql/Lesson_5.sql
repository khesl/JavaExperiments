-- select * from learn.employee;

/*select d.name, sum(e.salary) from learn.employee e
left join learn.department d ON d.id = e.department_id
group by d.id;*/

-- уникальные значения
-- select distinct(department_id) from learn.employee

-- 3 максимальных значения
-- select salary, name from learn.employee order by salary desc limit 3;
-- максимальное значения
-- select max(salary), name from learn.employee;

-- - Написать SQL­запросы, которые будут выводить:
-- ○ Список отделов с количеством сотрудников в каждом из них
/*select d.name, count(e.name) from learn.department d 
left join learn.employee e ON d.id = e.department_id
group by e.department_id;*/
select d.name, count(e.name) "количество сотрудников" from learn.employee e 
inner join learn.department d ON e.department_id = d.id
group by d.id;
        
-- ○ Список отделов, в которых работает меньше трёх сотрудников
select dd.name, ee.count_ "количество сотрудников" from learn.department dd 
left join (
	select e.department_id, d.name, count(e.name) count_ from learn.employee e 
		inner join learn.department d ON e.department_id = d.id
		group by d.id) ee 
ON dd.id = ee.department_id where ee.count_ < 3
group by ee.department_id




