CREATE TABLE db_example.role(
							id SMALLINT(4) AUTO_INCREMENT NOT NULL PRIMARY KEY,
							role_name VARCHAR(128) NOT NULL,
                            role_description VARCHAR(255)
);

CREATE TABLE db_example.employee(
								id SMALLINT(4) AUTO_INCREMENT NOT NULL PRIMARY KEY,
                                first_name varchar(64) NOT NULL,
                                last_name varchar(64) NOT NULL,
                                email varchar(64),
                                phone_number varchar(15),
                                national_id varchar(14),
                                age DATE,
                                role_id SMALLINT(4),
                                UNIQUE (id, email, national_id)
);

CREATE TABLE db_example.project(
								id SMALLINT(4) AUTO_INCREMENT NOT NULL PRIMARY KEY,
                                name VARCHAR(128),
                                start_date DATE,
                                project_manager_id SMALLINT(4)
);

CREATE TABLE db_example.emloyee_project_mapping(
												employee_id SMALLINT(4),
                                                project_id SMALLINT(4)
);