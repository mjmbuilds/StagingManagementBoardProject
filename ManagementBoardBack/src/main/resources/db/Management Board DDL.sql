DROP TABLE mb_user CASCADE CONSTRAINTS;
DROP TABLE mb_board CASCADE CONSTRAINTS;
DROP TABLE mb_category CASCADE CONSTRAINTS;
DROP TABLE mb_card CASCADE CONSTRAINTS;

CREATE TABLE mb_user (
    user_id VARCHAR2(36),
    user_firstname VARCHAR2(50) NOT NULL,
    user_lastname VARCHAR2(50) NOT NULL,
    user_username VARCHAR2(50) NOT NULL,
    user_password VARCHAR2(50) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE mb_board (
    board_id VARCHAR2(36),
    board_title VARCHAR2(50) NOT NULL,
    PRIMARY KEY (board_id)
);

CREATE TABLE mb_category (
    category_id VARCHAR2(36),
    category_title VARCHAR2(50) NOT NULL,
    PRIMARY KEY (category_id)
);

CREATE TABLE mb_card (
    card_id VARCHAR2(36),
    card_title VARCHAR2(50) NOT NULL,
    card_description VARCHAR2(1000) NOT NULL,
    PRIMARY KEY (card_id)
);