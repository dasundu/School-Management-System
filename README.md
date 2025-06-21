# 🏫 School Management System

A comprehensive web-based school management system designed to digitize and streamline daily academic and administrative operations. Built with Java (JSP/Servlets) and MySQL, this platform provides dedicated dashboards for administrators, teachers, and students with role-based access control.

## 📋 Table of Contents
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Usage](#-usage)
- [API Endpoints](#-api-endpoints)
- [Database Schema](#-database-schema)
- [Screenshots](#-screenshots)
- [Contributing](#-contributing)
- [License](#-license)

## ✨ Features

### 👥 User & Role Management
- **Secure Authentication**: Registration, login, and profile management
- **Role-Based Access Control**: Dedicated dashboards for Admin, Teacher, and Student roles
- **Session Management**: Secure session handling and user state management
- **OTP-Based Password Recovery**: Email-based password reset with OTP verification

### 📚 Academic Management
- **Class & Subject Management**: Complete CRUD operations for classes and subjects
- **Teacher-Subject Assignment**: Flexible assignment of teachers to subjects
- **Class Representative Selection**: Ability to assign and manage class representatives
- **Student Enrollment**: Streamlined student registration and subject enrollment

### 📝 Assignment Workflow
- **Assignment Creation**: Teachers can create and distribute assignments
- **Student Submission**: File upload and submission tracking
- **Evaluation System**: Grading and feedback mechanism
- **Progress Tracking**: Real-time assignment status monitoring

### 📊 Result Management
- **Grade Publishing**: Teachers can publish and manage student results
- **Academic Progress**: Students can view their academic performance
- **Result Analytics**: Comprehensive reporting and analytics
- **Performance Tracking**: Historical grade tracking and trends

### 🛡️ Security Features
- **Secure Authentication**: Password encryption and secure login
- **Session Management**: Automatic session timeout and security
- **Role-Based Authorization**: Granular access control
- **Data Validation**: Input validation and SQL injection prevention

## 🛠️ Tech Stack

### Frontend
- **HTML5** - Semantic markup and structure
- **CSS3** - Styling and responsive design
- **JavaScript** - Client-side interactivity

### Backend
- **Java** - Core programming language
- **JSP (JavaServer Pages)** - Dynamic web page generation
- **Servlets** - HTTP request handling
- **JDBC** - Database connectivity
- **Apache Tomcat** - Web server and servlet container

### Database
- **MySQL** - Relational database management

### Build & Tools
- **Apache Maven** - Dependency management and build automation
- **Git** - Version control

## 🏗️ Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Presentation  │    │    Business     │    │   Data Access   │
│     Layer       │    │     Logic       │    │     Layer       │
│                 │    │     Layer       │    │                 │
│ JSP Pages       │◄──►│   Servlets      │◄──►│   JDBC/DAO      │
│ HTML/CSS        │    │   Java Classes  │    │   MySQL         │
│ JavaScript      │    │   Session Mgmt  │    │   Database      │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## 📋 Prerequisites

Before running this application, ensure you have the following installed:

- **Java JDK 8+** 
- **Apache Tomcat 9.0+**
- **MySQL 8.0+**
- **Apache Maven 3.6+**
- **Git**

## 🚀 Installation

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/school-management-system.git
cd school-management-system
```

### 2. Database Setup
```sql
-- Create database
CREATE DATABASE school_management;

-- Import the database schema
mysql -u root -p school_management < database/schema.sql

-- Import sample data (optional)
mysql -u root -p school_management < database/sample_data.sql
```

### 3. Configure Database Connection
Update the database configuration in `src/main/resources/db.properties`:
```properties
db.url=jdbc:mysql://localhost:3306/school_management
db.username=your_username
db.password=your_password
db.driver=com.mysql.cj.jdbc.Driver
```

### 4. Build the Project
```bash
mvn clean compile
mvn package
```

### 5. Deploy to Tomcat
```bash
# Copy the generated WAR file to Tomcat webapps directory
cp target/school-management-system.war $TOMCAT_HOME/webapps/

# Start Tomcat server
$TOMCAT_HOME/bin/startup.sh
```

## ⚙️ Configuration

### Email Configuration (for OTP)
Configure email settings in `src/main/resources/email.properties`:
```properties
mail.smtp.host=smtp.gmail.com
mail.smtp.port=587
mail.smtp.auth=true
mail.smtp.starttls.enable=true
mail.username=your_email@gmail.com
mail.password=your_app_password
```

### Application Properties
Update `src/main/resources/app.properties`:
```properties
app.name=School Management System
app.version=1.0.0
session.timeout=30
otp.expiry.minutes=10
```

## 💻 Usage

### Access the Application
- **URL**: `http://localhost:8080/school-management-system`
- **Default Admin**: 
  - Username: `admin@school.com`
  - Password: `admin123`

### User Roles & Permissions

#### 👨‍💼 Administrator
- Manage users (students, teachers, admins)
- Configure classes and subjects
- Assign teachers to subjects
- View system reports and analytics
- Manage system settings

#### 👨‍🏫 Teacher
- View assigned classes and subjects
- Create and manage assignments
- Grade student submissions
- Publish results and feedback
- View student progress

#### 👨‍🎓 Student
- View enrolled subjects
- Submit assignments
- Check grades and results
- Update profile information
- View academic progress

## 🔗 API Endpoints

### Authentication
- `POST /login` - User login
- `POST /logout` - User logout
- `POST /register` - User registration
- `POST /forgot-password` - Password recovery
- `POST /reset-password` - Password reset with OTP

### User Management
- `GET /users` - List all users (Admin only)
- `POST /users` - Create new user
- `PUT /users/{id}` - Update user
- `DELETE /users/{id}` - Delete user

### Academic Management
- `GET /classes` - List all classes
- `POST /classes` - Create new class
- `GET /subjects` - List all subjects
- `POST /subjects` - Create new subject
- `POST /assignments` - Create assignment
- `GET /assignments/{id}` - Get assignment details

## 🗃️ Database Schema

### Key Tables
- **users** - User account information
- **roles** - User roles and permissions
- **classes** - Class information
- **subjects** - Subject details
- **assignments** - Assignment data
- **submissions** - Student submissions
- **results** - Academic results
- **user_sessions** - Session management

### Entity Relationships
```sql
users (1) -----> (N) user_roles (N) <----- (1) roles
users (1) -----> (N) enrollments (N) <----- (1) subjects
subjects (1) ----> (N) assignments (N) <----- (1) users
assignments (1) -> (N) submissions (N) <----- (1) users
```

## 📸 Screenshots

### Dashboard Views
![Admin Dashboard](screenshots/admin-dashboard.png)
*Admin Dashboard - Complete system overview*

![Teacher Dashboard](screenshots/teacher-dashboard.png)
*Teacher Dashboard - Class and assignment management*

![Student Dashboard](screenshots/student-dashboard.png)
*Student Dashboard - Academic progress tracking*

### Key Features
![Assignment Management](screenshots/assignment-management.png)
*Assignment creation and management interface*

![Result Management](screenshots/result-management.png)
*Grade publishing and result management*

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Development Guidelines
- Follow Java coding conventions
- Write meaningful commit messages
- Add appropriate comments and documentation
- Test your changes thoroughly
- Update README if needed

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Your Name**
- GitHub: [@yourusername](https://github.com/yourusername)
- LinkedIn: [Your LinkedIn](https://linkedin.com/in/yourprofile)
- Email: your.email@example.com

## 🙏 Acknowledgments

- Apache Tomcat community for the excellent servlet container
- MySQL team for the robust database system
- Maven community for the build automation tool
- All contributors and testers who helped improve this system

## 📞 Support

If you encounter any issues or have questions:

1. Check the [Issues](https://github.com/yourusername/school-management-system/issues) page
2. Create a new issue with detailed information
3. Contact the maintainer directly

---

⭐ **Star this repository if you find it helpful!**

---

*Last updated: June 2025*
