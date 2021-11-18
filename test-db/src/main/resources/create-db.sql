DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS request;

CREATE TABLE user
(
    id int NOT NULL auto_increment,
    name varchar(50) NOT NULL ,
    login varchar(50) NOT NULL,
    password varchar(50) NOT NULL,
    email varchar(50) NOT NULL ,
    CONSTRAINT user_pk PRIMARY KEY (id)
);

CREATE TABLE request
(
    idR int PRIMARY KEY auto_increment,
    id int NOT NULL,
    groupe varchar(50) NOT NULL ,
    pairs varchar(50) NOT NULL,
    subject varchar(50) NOT NULL,
    FOREIGN KEY (id)  REFERENCES user (id) ON DELETE CASCADE
);