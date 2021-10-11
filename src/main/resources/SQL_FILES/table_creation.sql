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

INSERT INTO db_example.role(role_name, role_description) VALUES("ASE","Associate Softwre Engineer");
INSERT INTO db_example.role(role_name, role_description) VALUES("SSE","Senior Software Egnineer");
INSERT INTO db_example.role(role_name, role_description) VALUES("MA","Manager");

INSERT INTO db_example.role VALUES(1, "SSE","Senior Software Egnineer");

INSERT INTO db_example.employee VALUES(1, "Shady", "Azouz", "shady.azouz@gmail.com", "01229339212", "12345678", 25, 1);

INSERT INTO db_example.employee VALUES(2, "Amr", "Soliman", "amr@gmail.com", "01229339212", "123456789", 24, 2);

INSERT INTO db_example.project VALUES(1, "Backend Training", 2021/10/10, 1);