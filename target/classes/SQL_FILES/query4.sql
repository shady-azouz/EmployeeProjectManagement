SELECT * 
FROM db_example.Employee e
WHERE
e.role_id = 2
AND NOT EXISTS (
SELECT 1
FROM db_example.employee_project_mapping epm
WHERE epm.employee_id = e.id);