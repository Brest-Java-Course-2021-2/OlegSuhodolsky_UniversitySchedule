DROP TABLE IF EXISTS request;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS groupe;

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
    groupe varchar(10) NOT NULL ,
    pairs varchar(2) NOT NULL,
    subject varchar(100) NOT NULL,
    date date NOT NULL,
    FOREIGN KEY (id)  REFERENCES user (id) ON DELETE CASCADE
);

CREATE TABLE groupe
(
    idGroupe int PRIMARY KEY auto_increment,
    nameGroupe varchar(10) NOT NULL UNIQUE
);

CREATE TABLE schedule
(
    idS int PRIMARY KEY auto_increment,
    lectorName varchar(55) NOT NULL,
    groupeName varchar(10) NOT NULL ,
    numberPair int NOT NULL,
    subject varchar(100) NOT NULL,
    day int NOT NULL
);


