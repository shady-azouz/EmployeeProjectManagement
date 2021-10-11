SELECT e.id, e.first_name, e.last_name, e.email, e.phone_number, e.national_id, e.age, e.role_id
FROM db_example.Project p
JOIN db_example.employee_project_mapping epm
ON p.id = epm.project_id
JOIN db_example.Employee e
ON e.id = epm.employee_id
WHERE p.name = "BackEnd Training";