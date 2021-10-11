SELECT *
FROM (SELECT * FROM db_example.Project p WHERE p.name = "project name")
INNER JOIN db_example.employee_project_mapping
INNER JOIN db_example.Employee;