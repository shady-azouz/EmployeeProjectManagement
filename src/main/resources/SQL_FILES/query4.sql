SELECT * 
FROM db_example.Employee e
WHERE
e.role_id = 2
AND NOT EXISTS (
SELECT 1
FROM db_example.project_employees pe
WHERE pe.employee_id = e.id);