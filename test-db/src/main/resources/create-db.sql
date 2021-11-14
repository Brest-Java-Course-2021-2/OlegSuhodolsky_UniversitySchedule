DROP TABLE IF EXISTS employee;

DROP TABLE IF EXISTS department;

CREATE TABLE user
(
    id int NOT NULL auto_increment,
    name varchar(50) NOT NULL UNIQUE,
    login varchar(50) NOT NULL UNIQUE,
    password varchar(50) NOT NULL,
    email varchar(50) NOT NULL ,
    PRIMARY KEY (id)
);

