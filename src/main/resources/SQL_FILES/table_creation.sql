CREATE TABLE db_example.role(
							id SMALLINT(4) AUTO_INCREMENT PRIMARY KEY,
							role_name VARCHAR(128) NOT NULL,
                            role_description VARCHAR(255)
);

CREATE TABLE db_example.employee(
								id SMALLINT(4) AUTO_INCREMENT PRIMARY KEY,
                                first_name varchar(64) NOT NULL,
                                last_name varchar(64) NOT NULL,
                                email varchar(64),
                                phone_number varchar(15),
                                national_id varchar(14),
                                age SMALLINT(3),
                                role_id SMALLINT(4),
                                UNIQUE (id, email, national_id)
);

CREATE TABLE db_example.project(
								id SMALLINT(4) AUTO_INCREMENT PRIMARY KEY,
                                name VARCHAR(128),
                                start_date DATE,
                                project_manager_id SMALLINT(4),
                                FOREIGN KEY(project_manager_id) REFERENCES employee(id)
);

CREATE TABLE db_example.employee_project_mapping(
												employee_id SMALLINT(4),
                                                project_id SMALLINT(4),
                                                FOREIGN KEY(employee_id) REFERENCES employee(id),
                                                FOREIGN KEY(project_id) REFERENCES project(id)
);