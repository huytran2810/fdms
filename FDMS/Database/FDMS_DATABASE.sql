CREATE DATABASE FDMS;
USE FDMS;

CREATE TABLE role
(
    role_id     BIGINT PRIMARY KEY,
    created_at  DATETIME,
    description VARCHAR(200),
    updated_at  DATETIME,
    role_name   VARCHAR(255)
);

CREATE TABLE userEntity
(
    id            BIGINT PRIMARY KEY,
    address       VARCHAR(100),
    avatar_image  VARCHAR(300),
    created_at    DATETIME,
    date_of_birth DATE,
    email         VARCHAR(100),
    full_name     VARCHAR(100),
    gender        VARCHAR(20),
    password      VARCHAR(255),
    phone         VARCHAR(20),
    status        VARCHAR(50),
    updated_at    DATETIME,
    user_name     VARCHAR(20),
    role_id       BIGINT,
    FOREIGN KEY (role_id) REFERENCES role (role_id)
);

CREATE TABLE student
(
    student_id  BIGINT PRIMARY KEY,
    create_at   DATETIME,
    description VARCHAR(200),
    parent_name VARCHAR(100),
    roll_number VARCHAR(10),
    updated_at  DATETIME,
    user_id     BIGINT,
    FOREIGN KEY (user_id) REFERENCES userEntity (id)
);

CREATE TABLE manager
(
    manager_id  BIGINT PRIMARY KEY,
    created_at  DATETIME,
    description VARCHAR(200),
    updated_at  DATETIME,
    user_id     BIGINT,
    FOREIGN KEY (user_id) REFERENCES userEntity (id)
);

CREATE TABLE feature
(
    feature_id   BIGINT PRIMARY KEY,
    created_at   DATETIME,
    feature_name VARCHAR(50),
    updated_at   DATETIME,
    url          VARCHAR(300)
);

CREATE TABLE role_feature
(
    role_id    BIGINT,
    feature_id BIGINT,
    PRIMARY KEY (role_id, feature_id),
    FOREIGN KEY (role_id) REFERENCES role (role_id),
    FOREIGN KEY (feature_id) REFERENCES feature (feature_id)
);

CREATE TABLE request_application_type
(
    request_application_type_id   BIGINT PRIMARY KEY,
    created_at                    DATETIME,
    request_application_type_name VARCHAR(300),
    updated_at                    DATETIME
);

CREATE TABLE request_application
(
    request_application_id      BIGINT PRIMARY KEY,
    created_at                  DATETIME,
    request_content             TEXT,
    status                      VARCHAR(255),
    text_response               TEXT,
    updated_at                  DATETIME,
    take_by_manager_id          BIGINT,
    request_application_type_id BIGINT,
    student_id                  BIGINT,
    FOREIGN KEY (take_by_manager_id) REFERENCES manager (manager_id),
    FOREIGN KEY (request_application_type_id) REFERENCES request_application_type (request_application_type_id),
    FOREIGN KEY (student_id) REFERENCES userEntity (id)
);

CREATE TABLE news
(
    news_id    BIGINT PRIMARY KEY,
    category   VARCHAR(50),
    content    TEXT,
    created_at DATETIME,
    image      TEXT,
    title      VARCHAR(255),
    updated_at DATETIME,
    manager_id BIGINT,
    FOREIGN KEY (manager_id) REFERENCES manager (manager_id)
);

CREATE TABLE semester
(
    semester_id   BIGINT PRIMARY KEY,
    created_at    DATETIME,
    end_date      DATE,
    semester_name VARCHAR(255),
    start_date    DATE,
    updated_at    DATETIME
);

CREATE TABLE room_type
(
    room_type_id         BIGINT PRIMARY KEY,
    created_at           DATETIME,
    room_type_desciption VARCHAR(200),
    room_type_name       VARCHAR(50),
    updated_at           DATETIME,
    number_of_bed        INT,
    price                FLOAT
);

CREATE TABLE building
(
    building_id   BIGINT PRIMARY KEY,
    building_name VARCHAR(255),
    created_at    DATETIME,
    updated_at    DATETIME,
    number_floor  INT
);

CREATE TABLE room
(
    room_id      BIGINT PRIMARY KEY,
    created_at   DATETIME,
    floor        INT,
    updated_at   DATETIME,
    room_name    VARCHAR(20),
    building_id  BIGINT,
    room_type_id BIGINT,
    room_price   FLOAT,
    FOREIGN KEY (building_id) REFERENCES building (building_id),
    FOREIGN KEY (room_type_id) REFERENCES room_type (room_type_id)
);

CREATE TABLE bed
(
    bed_id     BIGINT PRIMARY KEY,
    bed_name   VARCHAR(20),
    created_at DATETIME,
    status     VARCHAR(50),
    updated_at DATETIME,
    room_id    BIGINT,
    student_id BIGINT,
    FOREIGN KEY (room_id) REFERENCES room (room_id),
    FOREIGN KEY (student_id) REFERENCES student (student_id)
);

CREATE TABLE bed_request
(
    bed_request_id BIGINT PRIMARY KEY,
    created_at     DATETIME,
    status         VARCHAR(20),
    updated_at     DATETIME,
    bed_id         BIGINT,
    semester_id    BIGINT,
    student_id     BIGINT,
    FOREIGN KEY (bed_id) REFERENCES bed (bed_id),
    FOREIGN KEY (semester_id) REFERENCES semester (semester_id),
    FOREIGN KEY (student_id) REFERENCES student (student_id)
);

CREATE TABLE payment
(
    payment_id            BIGINT PRIMARY KEY,
    amount                FLOAT,
    created_at            DATETIME,
    status                VARCHAR(255),
    updated_at            DATETIME,
    bed_request_id        BIGINT,
    checked_by_manager_id BIGINT,
    student_id            BIGINT,
    semester_id           BIGINT,
    expiration_date       DATETIME,
    FOREIGN KEY (bed_request_id) REFERENCES bed_request (bed_request_id),
    FOREIGN KEY (checked_by_manager_id) REFERENCES manager (manager_id),
    FOREIGN KEY (student_id) REFERENCES student (student_id),
    FOREIGN KEY (semester_id) REFERENCES semester (semester_id)
);
Create table Used_eletric_and_water
(
    used_id    BIGINT PRIMARY KEY,
    room_id    BIGINT,
    electric   float,
    created_at DATETIME,
    water      float,
    updated_at DATETIME,
    FOREIGN KEY (room_id) REFERENCES room (room_id)

);

Create table bill
(
    billid      BIGINT PRIMARY KEY,
    student_id  BIGINT,
    semester_id bigint,
    amount      float,
    created_at  DATETIME,
    status      varchar(20),
    user_id     BIGINT,
    FOREIGN KEY (student_id) REFERENCES student (student_id),
    FOREIGN KEY (user_id) REFERENCES Used_eletric_and_water (used_id),
    FOREIGN KEY (semester_id) REFERENCES semester (semester_id)
)