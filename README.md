University Course Registration and Academic Management System

Student Name: Ndatimana Patrick
Registration No.: 26288
Course: Web Technology and Internet

1. Problem Analysis and Identified Entities
The university requires a system to manage departments, students, lecturers, courses, and registrations. Based on the problem statement, the following entities and attributes were identified:

1. Department: id, name, code, description, head_lecturer_id
2. Lecturer: id, first_name, last_name, email, phone, department_id
3. Student: id, first_name, last_name, email, registration_number, department_id
4. StudentProfile: id, date_of_birth, gender, phone_number, address, student_id
5. Course: id, code, title, credits, department_id
6. Registration (Join Entity): id, registration_date, academic_year, semester, status, grade, student_id, course_id

2. Relationships and Cardinalities
1. Department 1:N Course (FK: department_id). One department offers many courses.
2. Department 1:N Lecturer (FK: department_id). One department employs many lecturers.
3. Department 1:1 Lecturer (Head) (FK: head_lecturer_id - UNIQUE). Each department has exactly one head lecturer.
4. Student N:1 Department (FK: department_id). A student belongs to one department.
5. Student 1:1 StudentProfile (FK: student_id - UNIQUE). Each student has exactly one profile.
6. Lecturer M:N Course (Join table: lecturer_course). Lecturers teach multiple courses, and courses are taught by multiple lecturers. (Implemented as a Join Table without a separate entity, as instructed).
7. Student M:N Course (Join entity: Registration). Students register for multiple courses. (Implemented as a Join Entity because it carries extra attributes: date, year, semester, status, grade).

3. Entity Relationship Diagram (ERD)
(See the docs/ERD.png file in this repository for the visual diagram).
![ERD Diagram](docs/ERD.png)

4. Relational Database Schema
The database schema is generated using Hibernate. Here is the structure showing tables, Primary Keys (PK), and Foreign Keys (FK):

CREATE TABLE departments (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    code VARCHAR(20) UNIQUE NOT NULL,
    description TEXT,
    head_lecturer_id BIGINT UNIQUE, -- 1:1 with Lecturer
    FOREIGN KEY (head_lecturer_id) REFERENCES lecturers(id)
);

CREATE TABLE lecturers (
    id BIGINT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    department_id BIGINT,
    FOREIGN KEY (department_id) REFERENCES departments(id)
);

CREATE TABLE students (
    id BIGINT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    registration_number VARCHAR(30) UNIQUE NOT NULL,
    department_id BIGINT,
    FOREIGN KEY (department_id) REFERENCES departments(id)
);

CREATE TABLE student_profiles (
    id BIGINT PRIMARY KEY,
    date_of_birth DATE,
    gender VARCHAR(10),
    phone_number VARCHAR(20),
    address TEXT,
    student_id BIGINT UNIQUE, -- 1:1 with Student
    FOREIGN KEY (student_id) REFERENCES students(id)
);

CREATE TABLE courses (
    id BIGINT PRIMARY KEY,
    code VARCHAR(20) UNIQUE NOT NULL,
    title VARCHAR(150) NOT NULL,
    credits INT,
    department_id BIGINT,
    FOREIGN KEY (department_id) REFERENCES departments(id)
);

-- Join Table (M:N Lecturer and Course)
CREATE TABLE lecturer_course (
    lecturer_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    PRIMARY KEY (lecturer_id, course_id),
    FOREIGN KEY (lecturer_id) REFERENCES lecturers(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);

-- Join Entity (M:N Student and Course with extra attributes)
CREATE TABLE registrations (
    id BIGINT PRIMARY KEY,
    registration_date DATE NOT NULL,
    academic_year VARCHAR(20) NOT NULL,
    semester INT NOT NULL,
    status VARCHAR(20) NOT NULL, -- PENDING, APPROVED, REJECTED
    grade VARCHAR(5),
    student_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    UNIQUE (student_id, course_id, academic_year, semester),
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);

5. How to Run
1. Create database: CREATE DATABASE university_academic_db;
2. Update application.properties with DB credentials.
3. Run ./mvnw spring-boot:run

6. Screenshots
See docs/screenshots/ for table generation and constraint tests.