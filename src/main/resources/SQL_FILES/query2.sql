SELECT e 
FROM (SELECT * FROM Project p WHERE p.name = "project name")
INNER JOIN employee_project_mapping
INNER JOIN Employee e;