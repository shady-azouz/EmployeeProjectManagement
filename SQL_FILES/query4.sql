SELECT * 
FROM Employee e
WHERE NOT EXISTS (
SELECT 1
FROM employee_project_mapping epm
WHERE epm.project_id = 1)