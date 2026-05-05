-- USERS (base)
CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    email    VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    role     VARCHAR(20)         NOT NULL
);


CREATE TABLE admins
(
    id   BIGINT PRIMARY KEY,
    name VARCHAR(255),
    CONSTRAINT fk_admin_user FOREIGN KEY (id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE TABLE professors
(
    id   BIGINT PRIMARY KEY,
    name VARCHAR(255),
    CONSTRAINT fk_professor_user FOREIGN KEY (id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE students
(
    id                  BIGSERIAL PRIMARY KEY,
    name                VARCHAR(255),
    registration_number VARCHAR(100) UNIQUE,
    fingerprint_id      INTEGER UNIQUE
);

CREATE TABLE classes
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(255),
    professor_id BIGINT,
    CONSTRAINT fk_class_professor
        FOREIGN KEY (professor_id)
            REFERENCES professors (id)
            ON DELETE SET NULL
);

CREATE TABLE enrollments
(
    id         BIGSERIAL PRIMARY KEY,
    student_id BIGINT,
    class_id   BIGINT,
    CONSTRAINT fk_enrollment_student
        FOREIGN KEY (student_id)
            REFERENCES students (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_enrollment_class
        FOREIGN KEY (class_id)
            REFERENCES classes (id)
            ON DELETE CASCADE,
    CONSTRAINT uc_enrollment UNIQUE (student_id, class_id)
);

CREATE TABLE sessions
(
    id         BIGSERIAL PRIMARY KEY,
    class_id   BIGINT,
    start_time TIMESTAMP,
    end_time   TIMESTAMP,
    status     VARCHAR(20) NOT NULL,
    CONSTRAINT fk_session_class
        FOREIGN KEY (class_id)
            REFERENCES classes (id)
            ON DELETE CASCADE
);

CREATE TABLE attendances
(
    id         BIGSERIAL PRIMARY KEY,
    session_id BIGINT,
    student_id BIGINT,
    timestamp  TIMESTAMP,
    status     VARCHAR(20) NOT NULL,
    method     VARCHAR(20) NOT NULL,
    CONSTRAINT fk_attendance_session
        FOREIGN KEY (session_id)
            REFERENCES sessions (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_attendance_student
        FOREIGN KEY (student_id)
            REFERENCES students (id)
            ON DELETE CASCADE,
    CONSTRAINT uc_attendance UNIQUE (session_id, student_id)
);