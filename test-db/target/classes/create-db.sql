DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id int NOT NULL auto_increment,
    name varchar(50) NOT NULL ,
    login varchar(50) NOT NULL,
    password varchar(50) NOT NULL,
    email varchar(50) NOT NULL ,
    CONSTRAINT user_pk PRIMARY KEY (id)
);

