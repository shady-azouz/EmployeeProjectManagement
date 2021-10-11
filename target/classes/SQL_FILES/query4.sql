SELECT * 
FROM db_example.Employee
WHERE NOT EXISTS (
SELECT 1
FROM db_example.employee_project_mapping epm
WHERE epm.project_id = 1);