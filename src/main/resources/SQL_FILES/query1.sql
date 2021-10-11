SELECT e.id, e.first_name, e.last_name, e.email, e.phone_number, e.national_id, e.age, r.role_name, r.description
FROM db_example.Employee e
JOIN db_example.Role r
ON r.id = e.role_id;