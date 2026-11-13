# University Course Registration and Academic Management System

## Student Information
- **Name:** Ndatimana Patrick
- **Registration No:** 26288

## Technology Stack
- Java 17+
- Spring Boot
- Spring Data JPA / Hibernate
- PostgreSQL (Database: `university_academic_db`)
- Maven

## Identified Entities & Relationships
1. **Department**: Has many lecturers, many courses, many students, and one head lecturer.
2. **Lecturer**: Belongs to a department, teaches many courses (M:N).
3. **Student**: Belongs to a department, has one profile (1:1), registers for many courses (M:N).
4. **StudentProfile**: Belongs to one student (1:1).
5. **Course**: Belongs to a department, taught by many lecturers, has many registrations.
6. **Registration**: Join entity connecting Student and Course with extra attributes (date, year, semester, status, grade).

## Database Configuration
- PostgreSQL is running locally.
- Database created: `university_academic_db`.

## How to Run
1. Ensure PostgreSQL is running and the database exists.
2. Update database credentials in `src/main/resources/application.properties` if needed.
3. Run the application: `.\mvnw.cmd spring-boot:run`

## Screenshots
Screenshots demonstrating the generated tables and relationship mappings can be found in the `screenshots/` folder (if applicable).
## Database ERD (Entity Relationship Diagram)

```mermaid
erDiagram
    DEPARTMENT ||--o| LECTURER : "has head"
    DEPARTMENT ||--o{ LECTURER : "employs"
    DEPARTMENT ||--o{ COURSE : "offers"
    DEPARTMENT ||--o{ STUDENT : "admits"
    
    STUDENT ||--|| STUDENT_PROFILE : "has"
    STUDENT ||--o{ REGISTRATION : "registers"
    COURSE ||--o{ REGISTRATION : "has"
    
    LECTURER }|--|{ COURSE : "teaches"

    DEPARTMENT {
        Long id PK
        String name
        String code
        Long head_lecturer_id FK
    }
    LECTURER {
        Long id PK
        String firstName
        String lastName
        String email
        Long department_id FK
    }
    STUDENT {
        Long id PK
        String registrationNumber
        String firstName
        String lastName
        Long department_id FK
    }
    STUDENT_PROFILE {
        Long id PK
        LocalDate dateOfBirth
        String gender
        String phoneNumber
        Long student_id FK
    }
    COURSE {
        Long id PK
        String code
        String title
        Long department_id FK
    }
    REGISTRATION {
        Long id PK
        LocalDate registrationDate
        String academicYear
        Integer semester
        String status
        String grade
        Long student_id FK
        Long course_id FK
    }