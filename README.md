# Course Management System

A terminal-based Course Management System developed in Java that simulates academic course registration and administration workflows within a university environment. The system implements role-based access control, object-oriented design principles, custom exception handling, and UML-driven software architecture.

The project was designed to model interactions among students, teaching assistants, professors, and administrators while providing functionalities such as course registration, grade management, complaint handling, and academic administration.

---

## Overview

This application demonstrates the practical implementation of Object-Oriented Programming (OOP) concepts through a multi-user academic management platform. The system provides dedicated functionalities for different user roles and enforces academic policies through custom business logic and exception handling.

The design emphasizes modularity, extensibility, and maintainability, making it suitable for future integration with graphical interfaces or database systems.

---

## Key Features

### Student Module

- View available courses
- Register for courses
- Drop registered courses
- Track academic progress
- Submit complaints and feedback
- View grades and course information

### Teaching Assistant (TA) Module

- Inherits student functionality
- Assist professors in course management
- Access and review student records
- Support grading-related operations

### Professor Module

- Manage assigned courses
- Update student grades
- Review course enrollment
- Handle academic records

### Administrator Module

- Manage course catalog
- Monitor enrollment activities
- Handle system-wide operations
- Resolve administrative requests

---

## Software Engineering Concepts

### Object-Oriented Programming

- Encapsulation
- Inheritance
- Polymorphism
- Abstraction

### Design Features

- Role-Based Access Control (RBAC)
- UML-Based System Design
- Modular Architecture
- Custom Exception Handling
- Generic Programming

### Exception Handling

The system implements custom exceptions to enforce academic policies:

- `CourseFullException`
- `DropDeadlinePassedException`

These exceptions ensure data consistency and realistic simulation of university regulations.

---

## Class Structure

```text
User
│
├── Student
│     │
│     └── TeachingAssistant
│
├── Professor
│
└── Administrator

Course
Complaint
GenericPair
```

The architecture follows inheritance hierarchies to reduce code duplication and promote reusability.

---

## UML Design

The project includes a UML class diagram illustrating:

- Class relationships
- Inheritance hierarchy
- Associations
- System architecture

### UML Diagram

![UML Diagram](UML%20Final.png)

---

## Technical Highlights

- Java-based application
- Terminal-driven user interface
- Custom exception framework
- Generic programming implementation
- Role-specific access management
- UML-guided software development

---

## Project Structure

```text
Course_management_system
│
├── Admin.java
├── Student.java
├── TeachingAssistant.java
├── Professor.java
├── User.java
│
├── Course.java
├── Complaint.java
├── GenericPair.java
│
├── CourseFullException.java
├── DropDeadlinePassedException.java
│
├── Main.java
│
├── UML Final.png
└── README.md
```

---

## Installation

### Prerequisites

- Java Development Kit (JDK 17 or later)

### Clone Repository

```bash
git clone https://github.com/yuvraj-23617/Course_management_system.git
cd Course_management_system
```

### Compile

```bash
javac *.java
```

### Run

```bash
java Main
```

---

## Sample Workflow

```text
User Login
      │
      ▼
Role Identification
      │
      ├── Student
      ├── Teaching Assistant
      ├── Professor
      └── Administrator
      │
      ▼
Role-Specific Operations
      │
      ▼
Course Management / Registration
      │
      ▼
Academic Record Updates
```

---

## Learning Outcomes

Through this project, the following software engineering concepts were applied:

- Object-Oriented Analysis and Design
- UML Modeling
- Role-Based System Architecture
- Exception Handling
- Generic Programming
- Modular Application Development
- Academic Workflow Simulation

---

## Future Enhancements

- Database integration using MySQL
- Graphical User Interface (JavaFX)
- Authentication and authorization framework
- Persistent data storage
- REST API support
- Multi-semester course management
- Academic analytics dashboard

---

## Author

**Yuvraj Verma**

Computer Science and Bioscience  
Indraprastha Institute of Information Technology Delhi (IIIT-Delhi)

GitHub: https://github.com/yuvraj-23617

---

## License

This project is intended for educational and academic purposes.
