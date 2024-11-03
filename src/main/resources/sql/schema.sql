CREATE DATABASE IF NOT EXISTS capstone;

USE capstone;

CREATE TABLE branch
(
    id                  INT AUTO_INCREMENT NOT NULL,
    created_at          DATETIME           NOT NULL,
    updated_at          DATETIME           NULL,
    distance            DECIMAL(9, 2)     NULL,
    lat                 DECIMAL(10, 7)     NULL,
    lon                 DECIMAL(10, 7)     NULL,
    rating              DECIMAL(4, 2)      NULL,
    total_cineplex_hall INT                NULL,
    address             VARCHAR(255)       NULL,
    avatar              VARCHAR(255)       NULL,
    link                VARCHAR(255)       NULL,
    logo                VARCHAR(255)       NULL,
    name                VARCHAR(255)       NULL,
    cinema_id           INT                NULL,
    district_id         INT                NULL,
    CONSTRAINT pk_branch PRIMARY KEY (id)
);

CREATE TABLE branch_movie
(
    branch_id INT NOT NULL,
    movie_id  INT NOT NULL,
    CONSTRAINT pk_branch_movie PRIMARY KEY (branch_id, movie_id)
);

CREATE TABLE cinema
(
    id                INT AUTO_INCREMENT NOT NULL,
    created_at        DATETIME           NOT NULL,
    updated_at        DATETIME           NULL,
    total_cinema_hall INT                NULL,
    image             VARCHAR(255)       NULL,
    name              VARCHAR(255)       NULL,
    CONSTRAINT pk_cinema PRIMARY KEY (id)
);

CREATE TABLE cinema_provinces
(
    cinema_id   INT NOT NULL,
    province_id INT NOT NULL,
    CONSTRAINT pk_cinema_provinces PRIMARY KEY (cinema_id, province_id)
);

CREATE TABLE districts
(
    id           INT AUTO_INCREMENT NOT NULL,
    created_at   DATETIME           NOT NULL,
    updated_at   DATETIME           NULL,
    code_name    VARCHAR(255)       NULL,
    full_name    VARCHAR(255)       NULL,
    full_name_en VARCHAR(255)       NULL,
    name         VARCHAR(255)       NULL,
    name_en      VARCHAR(255)       NULL,
    province_id  INT                NULL,
    CONSTRAINT pk_districts PRIMARY KEY (id)
);

CREATE TABLE hall
(
    id          INT AUTO_INCREMENT NOT NULL,
    created_at  DATETIME           NOT NULL,
    updated_at  DATETIME           NULL,
    total_seats INT                NULL,
    name        VARCHAR(255)       NULL,
    branch_id   INT                NULL,
    CONSTRAINT pk_hall PRIMARY KEY (id)
);

CREATE TABLE movie
(
    id         INT AUTO_INCREMENT NOT NULL,
    created_at DATETIME           NOT NULL,
    updated_at DATETIME           NULL,
    rating     TINYINT            NULL,
    time       INT                NULL,
    start_date DATETIME           NULL,
    name       VARCHAR(255)       NULL,
    poster     VARCHAR(255)       NULL,
    trailer    VARCHAR(255)       NULL,
    CONSTRAINT pk_movie PRIMARY KEY (id)
);

CREATE TABLE provinces
(
    id           INT AUTO_INCREMENT NOT NULL,
    created_at   DATETIME           NOT NULL,
    updated_at   DATETIME           NULL,
    code_name    VARCHAR(255)       NULL,
    full_name    VARCHAR(255)       NULL,
    full_name_en VARCHAR(255)       NULL,
    name         VARCHAR(255)       NULL,
    name_en      VARCHAR(255)       NULL,
    CONSTRAINT pk_provinces PRIMARY KEY (id)
);

CREATE TABLE reservation
(
    id               INT AUTO_INCREMENT NOT NULL,
    created_at       DATETIME           NOT NULL,
    updated_at       DATETIME           NULL,
    time_reservation DATETIME           NULL,
    total_price      DOUBLE             NULL,
    status           VARCHAR(255)       NULL,
    screening_id     INT                NULL,
    user_id          INT                NULL,
    CONSTRAINT pk_reservation PRIMARY KEY (id)
);

CREATE TABLE screening
(
    id         INT AUTO_INCREMENT NOT NULL,
    created_at DATETIME           NOT NULL,
    updated_at DATETIME           NULL,
    start_time DATETIME           NULL,
    end_time   DATETIME           NULL,
    status     VARCHAR(255)       NULL,
    hall_id    INT                NULL,
    movie_id   INT                NULL,
    CONSTRAINT pk_screening PRIMARY KEY (id)
);

CREATE TABLE screening_seat
(
    id           INT AUTO_INCREMENT NOT NULL,
    created_at   DATETIME           NOT NULL,
    updated_at   DATETIME           NULL,
    is_booked    BIT(1)             NULL,
    is_active    BIT(1)             NULL,
    seat_column  INT                NULL,
    seat_row     VARCHAR(255)       NULL,
    seat_number  INT                NULL,
    seat_code    VARCHAR(255)       NULL,
    price        DOUBLE             NULL,
    seat_type_id INT                NULL,
    screening_id INT                NULL,
    CONSTRAINT pk_screening_seat PRIMARY KEY (id)
);

CREATE TABLE seat
(
    id           INT AUTO_INCREMENT NOT NULL,
    created_at   DATETIME           NOT NULL,
    updated_at   DATETIME           NULL,
    is_active    BIT(1)             NULL,
    seat_column  INT                NULL,
    seat_row     VARCHAR(255)       NULL,
    seat_number  INT                NULL,
    seat_code    VARCHAR(255)       NULL,
    price        DOUBLE             NULL,
    hall_id      INT                NULL,
    seat_type_id INT                NULL,
    CONSTRAINT pk_seat PRIMARY KEY (id)
);

CREATE TABLE seat_reservation
(
    id                INT AUTO_INCREMENT NOT NULL,
    created_at        DATETIME           NOT NULL,
    updated_at        DATETIME           NULL,
    price             DOUBLE             NULL,
    reservation_id    INT                NULL,
    screening_id      INT                NULL,
    screening_seat_id INT                NULL,
    CONSTRAINT pk_seat_reservation PRIMARY KEY (id)
);

CREATE TABLE seat_type
(
    id         INT AUTO_INCREMENT NOT NULL,
    created_at DATETIME           NOT NULL,
    updated_at DATETIME           NULL,
    name       VARCHAR(255)       NULL,
    CONSTRAINT pk_seat_type PRIMARY KEY (id)
);

CREATE TABLE user
(
    id                INT AUTO_INCREMENT NOT NULL,
    created_at        DATETIME           NOT NULL,
    updated_at        DATETIME           NULL,
    is_email_verified BIT(1)             NULL,
    is_phone_verified BIT(1)             NULL,
    phone             VARCHAR(255)       NULL,
    `role`            VARCHAR(255)       NULL,
    email             VARCHAR(255)       NULL,
    first_name        VARCHAR(255)       NULL,
    last_name         VARCHAR(255)       NULL,
    full_name         VARCHAR(255)       NULL,
    avatar            VARCHAR(255)       NULL,
    password          VARCHAR(255)       NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE branch
    ADD CONSTRAINT FK_BRANCH_ON_CINEMA FOREIGN KEY (cinema_id) REFERENCES cinema (id);

ALTER TABLE branch
    ADD CONSTRAINT FK_BRANCH_ON_DISTRICT FOREIGN KEY (district_id) REFERENCES districts (id);

ALTER TABLE cinema_provinces
    ADD CONSTRAINT FK_CINEMA_PROVINCES_ON_CINEMA FOREIGN KEY (cinema_id) REFERENCES cinema (id);

ALTER TABLE cinema_provinces
    ADD CONSTRAINT FK_CINEMA_PROVINCES_ON_PROVINCE FOREIGN KEY (province_id) REFERENCES provinces (id);

ALTER TABLE districts
    ADD CONSTRAINT FK_DISTRICTS_ON_PROVINCE FOREIGN KEY (province_id) REFERENCES provinces (id);

ALTER TABLE hall
    ADD CONSTRAINT FK_HALL_ON_BRANCH FOREIGN KEY (branch_id) REFERENCES branch (id);

ALTER TABLE reservation
    ADD CONSTRAINT FK_RESERVATION_ON_SCREENING FOREIGN KEY (screening_id) REFERENCES screening (id);

ALTER TABLE reservation
    ADD CONSTRAINT FK_RESERVATION_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE screening
    ADD CONSTRAINT FK_SCREENING_ON_HALL FOREIGN KEY (hall_id) REFERENCES hall (id);

ALTER TABLE screening
    ADD CONSTRAINT FK_SCREENING_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movie (id);

ALTER TABLE screening_seat
    ADD CONSTRAINT FK_SCREENING_SEAT_ON_SCREENING FOREIGN KEY (screening_id) REFERENCES screening (id);

ALTER TABLE screening_seat
    ADD CONSTRAINT FK_SCREENING_SEAT_ON_SEAT_TYPE FOREIGN KEY (seat_type_id) REFERENCES seat_type (id);

ALTER TABLE seat
    ADD CONSTRAINT FK_SEAT_ON_HALL FOREIGN KEY (hall_id) REFERENCES hall (id);

ALTER TABLE seat
    ADD CONSTRAINT FK_SEAT_ON_SEAT_TYPE FOREIGN KEY (seat_type_id) REFERENCES seat_type (id);

ALTER TABLE seat_reservation
    ADD CONSTRAINT FK_SEAT_RESERVATION_ON_RESERVATION FOREIGN KEY (reservation_id) REFERENCES reservation (id);

ALTER TABLE seat_reservation
    ADD CONSTRAINT FK_SEAT_RESERVATION_ON_SCREENING FOREIGN KEY (screening_id) REFERENCES screening (id);

ALTER TABLE seat_reservation
    ADD CONSTRAINT FK_SEAT_RESERVATION_ON_SCREENING_SEAT FOREIGN KEY (screening_seat_id) REFERENCES screening_seat (id);

ALTER TABLE branch_movie
    ADD CONSTRAINT fk_bramov_on_branch_entity FOREIGN KEY (branch_id) REFERENCES branch (id);

ALTER TABLE branch_movie
    ADD CONSTRAINT fk_bramov_on_movie_entity FOREIGN KEY (movie_id) REFERENCES movie (id);