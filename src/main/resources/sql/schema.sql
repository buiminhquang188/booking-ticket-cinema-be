CREATE DATABASE IF NOT EXISTS capstone;

USE capstone;

CREATE TABLE IF NOT EXISTS cinema
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    total_cinema_hall INT          NULL,
    created_at        DATETIME(6)  NOT NULL,
    updated_at        DATETIME(6)  NULL,
    image             VARCHAR(255) NULL,
    name              VARCHAR(255) NULL
    );

CREATE TABLE IF NOT EXISTS cinema_provinces
(
    cinema_id   INT NOT NULL,
    province_id INT NOT NULL
);

ALTER TABLE cinema_provinces
    ADD PRIMARY KEY (cinema_id, province_id);

ALTER TABLE cinema_provinces
    ADD CONSTRAINT FK_cinema_id_cinema_provinces
        FOREIGN KEY (cinema_id) REFERENCES cinema (id);

CREATE TABLE IF NOT EXISTS branch
(
    cinema_id           INT             NULL,
    distance            DECIMAL(16, 15) NULL,
    district_id         INT             NULL,
    id                  INT AUTO_INCREMENT
    PRIMARY KEY,
    lat                 DECIMAL(5, 2)   NULL,
    lon                 DECIMAL(5, 2)   NULL,
    rating              DECIMAL(3, 2)   NULL,
    total_cineplex_hall INT             NULL,
    created_at          DATETIME(6)     NOT NULL,
    updated_at          DATETIME(6)     NULL,
    address             VARCHAR(255)    NULL,
    avatar              VARCHAR(255)    NULL,
    link                VARCHAR(255)    NULL,
    logo                VARCHAR(255)    NULL,
    name                VARCHAR(255)    NULL
    );

ALTER TABLE branch
    ADD CONSTRAINT FK_cinema_id_branch
        FOREIGN KEY (cinema_id) REFERENCES cinema (id);

CREATE TABLE IF NOT EXISTS hall
(
    branch_id   INT         NULL,
    id          INT AUTO_INCREMENT
    PRIMARY KEY,
    total_seats INT         NULL,
    created_at  DATETIME(6) NOT NULL,
    updated_at  DATETIME(6) NULL,
    name        VARCHAR(10) NULL
    );

ALTER TABLE hall
    ADD CONSTRAINT FK_branch_id_hall
        FOREIGN KEY (branch_id) REFERENCES hall (id);

CREATE TABLE IF NOT EXISTS seat
(
    hall_id     INT         NULL,
    id          INT AUTO_INCREMENT
    PRIMARY KEY,
    is_active   BIT         NULL,
    seat_column INT         NULL,
    seat_number INT         NULL,
    seat_row    VARCHAR(3)  NULL,
    seat_code   VARCHAR(5)  NULL,
    created_at  DATETIME(6) NOT NULL,
    updated_at  DATETIME(6) NULL
    );

ALTER TABLE seat
    ADD CONSTRAINT FK_hall_id_seat
        FOREIGN KEY (hall_id) REFERENCES hall (id);

CREATE TABLE IF NOT EXISTS districts
(
    id           INT AUTO_INCREMENT
    PRIMARY KEY,
    province_id  INT          NOT NULL,
    created_at   DATETIME(6)  NOT NULL,
    updated_at   DATETIME(6)  NULL,
    code_name    VARCHAR(25)  NOT NULL,
    full_name    VARCHAR(255) NOT NULL,
    full_name_en VARCHAR(255) NOT NULL,
    name         VARCHAR(255) NOT NULL,
    name_en      VARCHAR(255) NOT NULL
    );

ALTER TABLE branch
    ADD CONSTRAINT FK_district_id_branch
        FOREIGN KEY (district_id) REFERENCES districts (id);

CREATE TABLE IF NOT EXISTS movie
(
    id         INT AUTO_INCREMENT
    PRIMARY KEY,
    rating     TINYINT      NULL,
    time       INT          NULL,
    created_at DATETIME(6)  NOT NULL,
    start_date DATETIME(6)  NULL,
    updated_at DATETIME(6)  NULL,
    movie_name VARCHAR(255) NULL,
    poster     VARCHAR(255) NULL,
    trailer    VARCHAR(255) NULL
    );

CREATE TABLE IF NOT EXISTS branch_movie
(
    branch_id   INT NOT NULL,
    movie_id    INT NOT NULL
);

ALTER TABLE branch_movie
    ADD PRIMARY KEY (branch_id, movie_id);

ALTER TABLE branch_movie
    ADD CONSTRAINT FK_branch_id_branch_movie
        FOREIGN KEY (branch_id) REFERENCES branch (id);

ALTER TABLE branch_movie
    ADD CONSTRAINT FK_movie_id_branch_movie
        FOREIGN KEY (movie_id) REFERENCES movie (id);

CREATE TABLE IF NOT EXISTS provinces
(
    id           INT AUTO_INCREMENT
    PRIMARY KEY,
    created_at   DATETIME(6)  NOT NULL,
    updated_at   DATETIME(6)  NULL,
    code_name    VARCHAR(25)  NULL,
    full_name    VARCHAR(255) NULL,
    full_name_en VARCHAR(255) NULL,
    name         VARCHAR(255) NULL,
    name_en      VARCHAR(255) NULL
    );

ALTER TABLE cinema_provinces
    ADD CONSTRAINT FK_province_id_cinema_provinces
        FOREIGN KEY (province_id) REFERENCES provinces (id);

ALTER TABLE districts
    ADD CONSTRAINT FK_province_id_districts
        FOREIGN KEY (province_id) REFERENCES provinces (id);

CREATE TABLE IF NOT EXISTS reservation
(
    id               INT AUTO_INCREMENT
    PRIMARY KEY,
    screening_id     INT          NULL,
    user_id          INT          NULL,
    created_at       DATETIME(6)  NOT NULL,
    time_reservation DATETIME(6)  NULL,
    updated_at       DATETIME(6)  NULL,
    status           VARCHAR(255) NULL
    );

CREATE TABLE IF NOT EXISTS screening
(
    hall_id    INT         NULL,
    id         INT AUTO_INCREMENT
    PRIMARY KEY,
    movie_id   INT         NULL,
    created_at DATETIME(6) NOT NULL,
    end_time   DATETIME(6) NULL,
    start_time DATETIME(6) NULL,
    updated_at DATETIME(6) NULL
    );

ALTER TABLE reservation
    ADD CONSTRAINT FK_screening_id_reservation
        FOREIGN KEY (screening_id) REFERENCES screening (id);

ALTER TABLE screening
    ADD CONSTRAINT FK_hall_id_screening
        FOREIGN KEY (hall_id) REFERENCES hall (id);

ALTER TABLE screening
    ADD CONSTRAINT FK_movie_id_screening
        FOREIGN KEY (movie_id) REFERENCES movie (id);

CREATE TABLE IF NOT EXISTS seat_reservation
(
    seat_id   INT         NULL,
    id             INT AUTO_INCREMENT
    PRIMARY KEY,
    is_reserved    BIT         NULL,
    price          DOUBLE      NULL,
    reservation_id INT         NULL,
    screening_id   INT         NULL,
    created_at     DATETIME(6) NOT NULL,
    updated_at     DATETIME(6) NULL
    );

ALTER TABLE seat_reservation
    ADD CONSTRAINT FK_reservation_id_seat_reservation
        FOREIGN KEY (reservation_id) REFERENCES reservation (id);

ALTER TABLE seat_reservation
    ADD CONSTRAINT FK_screening_id_seat_reservation
        FOREIGN KEY (screening_id) REFERENCES screening (id);

ALTER TABLE seat_reservation
    ADD CONSTRAINT FK_seat_id_seat_reservation
        FOREIGN KEY (seat_id) REFERENCES seat (id);

CREATE TABLE IF NOT EXISTS user
(
    id                INT AUTO_INCREMENT
    PRIMARY KEY,
    is_email_verified BIT          NULL,
    is_phone_verified BIT          NULL,
    created_at        DATETIME(6)  NOT NULL,
    updated_at        DATETIME(6)  NULL,
    phone             VARCHAR(20)  NULL,
    role              VARCHAR(20)  NULL,
    email             VARCHAR(50)  NOT NULL,
    first_name        VARCHAR(50)  NULL,
    last_name         VARCHAR(50)  NULL,
    full_name         VARCHAR(100) NULL,
    avatar            VARCHAR(255) NULL,
    password          VARCHAR(255) NULL
    );

ALTER TABLE reservation
    ADD CONSTRAINT FK_user_id_reservation
        FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user
    ADD CONSTRAINT UK_user
        UNIQUE (email);
