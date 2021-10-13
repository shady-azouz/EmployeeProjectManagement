SELECT e.id, e.first_name, e.last_name, e.email, e.phone_number, e.national_id, e.age, e.role_id
FROM db_example.Project p
JOIN db_example.project_employees pe
ON p.id = pe.project_id
JOIN db_example.Employee e
ON e.id = pe.employee_id
WHERE p.name = "BackEnd Training";