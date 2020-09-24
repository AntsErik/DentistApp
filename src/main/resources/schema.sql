DROP TABLE IF EXISTS visits;

CREATE TABLE visits(
id int(11) NOT NULL AUTO_INCREMENT,
dentist_name varchar(45) DEFAULT NULL,
visit_time TIMESTAMP,
PRIMARY KEY(`id`)
);