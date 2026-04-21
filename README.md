# Course Change Management System

## 📌 Overview

This project is a Java-based application designed to manage course change requests within an academic environment. It provides a structured system where users can submit, track, and manage course change processes efficiently.

The system follows a modular architecture, separating concerns across data access, business logic, and user interface layers.

---

## ⚙️ Technologies Used

* Java (JDK 21)
* JavaFX (for UI)
* Maven (project management and dependencies)
* Object-Oriented Programming (OOP principles)

---

## 🏗️ Project Structure

The application is organized into the following packages:

* **dao/**
  Handles data access operations and interaction with the data source.

* **model/**
  Contains core business entities and data models.

* **ui/**
  Manages the user interface components and user interaction.

* **notification/**
  Handles system notifications and messaging logic.

* **util/**
  Utility classes for common functions and shared logic.

* **controllers/**
  Includes:

  * `LoginController` – Handles authentication logic
  * `PrimaryController` – Main application controller
  * `SecondaryController` – Supports additional UI interactions
  * `SessionManager` – Manages user sessions

---

## 🚀 Features

* User authentication system (login handling)
* Structured navigation across application screens
* Modular design for scalability and maintainability
* Separation of concerns using layered architecture
* Session management for user interactions

---

▶️ How to Run the Project

Clone the repository:

git clone https://github.com/Allstarhudd/CourseChange.git
Open the project in an IDE (e.g., VS Code or IntelliJ)
Ensure Java 21 is installed and configured

Build the project using Maven:

mvn clean install
Run the application from the main class (App)
## 📈 Future Improvements

* Add database integration (e.g., MySQL or PostgreSQL)
* Implement role-based access control (admin/student)
* Improve UI/UX design
* Add reporting and analytics features
* Deploy as a web-based system

---

## 👤 Author

Nkunja Justus Mwenda

---

## 📄 License

This project is for academic and learning purposes.
