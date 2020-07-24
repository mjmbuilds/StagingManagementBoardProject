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
    CONSTRAINT pk_user PRIMARY KEY (user_id)
);

CREATE TABLE mb_board (
    board_id VARCHAR2(36),
    user_id VARCHAR2(36),
    board_title VARCHAR2(50) NOT NULL,
    CONSTRAINT pk_board PRIMARY KEY (board_id)
);

CREATE TABLE mb_category (
    category_id VARCHAR2(36),
    board_id VARCHAR2(36),
    category_title VARCHAR2(50) NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (category_id)
);

CREATE TABLE mb_card (
    card_id VARCHAR2(36),
    category_id VARCHAR2(36),
    card_title VARCHAR2(50) NOT NULL,
    card_description VARCHAR2(1000) NOT NULL,
    CONSTRAINT pk_card PRIMARY KEY (card_id)
);

ALTER TABLE mb_board
    ADD CONSTRAINT fk_board_user FOREIGN KEY ( user_id )
        REFERENCES mb_user ( user_id );

ALTER TABLE mb_category
    ADD CONSTRAINT fk_category_board FOREIGN KEY ( board_id )
        REFERENCES mb_board ( board_id );

ALTER TABLE mb_card
    ADD CONSTRAINT fk_card_category FOREIGN KEY ( category_id )
        REFERENCES mb_category ( category_id );

COMMIT;