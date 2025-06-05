# 🏫 School Management System

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)
![Swing](https://img.shields.io/badge/Java_Swing-GUI-yellowgreen)

A comprehensive desktop application for managing school operations with role-based access control.

## 📖 Project Description

The **School Management System** is a robust desktop application designed to streamline administrative and academic operations in educational institutions. Built with Java Swing and MySQL, this system provides:

- **Role-based access control** for four user types (Admin, Manager, Teachers, Students)
- **Centralized data management** for all school entities
- **Real-time communication** through notice broadcasting
- **Assignment lifecycle management** from creation to submission tracking

Key differentiators:
- 🛡️ **Secure authentication** with password encryption
- 📈 **Data visualization** for academic performance tracking
- 🔄 **CRUD operations** for all core entities
- 📱 **Desktop-first design** with mobile expansion roadmap

Ideal for:
- Small to medium schools looking to digitize operations
- Institutions needing to replace paper-based processes
- IT courses demonstrating full-stack Java applications

## ✨ Features

### 👨‍💼 Admin
- View teacher and student records
- System monitoring and access control

### 📊 Manager
- **CRUD operations** for teacher/student records
- Send notices to teachers, students, and admin
- Generate reports

### 👩‍🏫 Teachers
- Assign homework with due dates
- Manage subject-specific assignments
- Track student submissions

### 🧑‍🎓 Students
- View assigned homework
- Check deadlines
- Receive notices

## 🛠️ Technology Stack

| Component        | Technology |
|------------------|------------|
| Frontend         | Java Swing |
| Backend          | Java JDBC  |
| Database         | MySQL 8.0  |
| IDE              | IntelliJ IDEA |


## 🚀 Installation

 **Prerequisites**:
   - Java 17+
   - MySQL 8.0
   - IntelliJ IDEA (recommended)
`

 ## 🗃️ Database Schema

### 📊 Core Tables Overview
```mermaid
erDiagram
    users ||--o{ teachers : "1:1"
    users ||--o{ students : "1:1"
    teachers ||--o{ assignments : "1:N"
    assignments ||--o{ submissions : "1:N"
    notices ||--o{ user_notices : "1:N"
