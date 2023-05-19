DROP DATABASE IF EXISTS `userinfo`;
CREATE DATABASE userinfo;
USE userinfo;

CREATE TABLE users (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (username)
);

INSERT INTO users (username, password)
VALUES ('0987654321', 'q2ư2e3'),
       ('0987452100', 'so1dcv'),
       ('0983313567', 'ki98u7'),
       ('0912345678', 'ngaythu5');