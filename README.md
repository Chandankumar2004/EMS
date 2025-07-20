# 🧑‍💼 Employee Management System (Java + Swing + MySQL)

A full-featured **Employee Management System** built using Java Swing for UI and MySQL for backend database integration.

---

## 📸 Project Screenshots

> Below are step-by-step images located in the `projectEMSImg/` folder:

### 1. 🏠 Home Screen
![Home Screen](projectEMSImg/home.png)

### 2. ➕ Onboard Employee
![Onboard Employee](projectEMSImg/onboard.png)

### 3. 📋 View All Employees
![View All](projectEMSImg/displayall.png)

### 4. 🔍 Search Employee
![Search](projectEMSImg/singleemployee.png)

### 5. ✏️ Update Employee
#### a. Enter ID to Update  
![Update Step 1](projectEMSImg/updateemployee_step1.png)

#### b. Edit & Save Details  
![Update Step 2](projectEMSImg/updateemployee_step2.png)

### 6. ❌ De-board Employee
![DeBoard](projectEMSImg/deboard.png)

---

## 🚀 Features

- ➕ Add New Employee  
- 📋 Display All Employees  
- 🔍 Search by Employee ID  
- ✏️ Update Employee Records  
- ❌ Remove/De-board Employee  
- 🖥️ Responsive GUI using Java Swing  
- 🛢️ MySQL Database Integration  

---

## 🛠 Tech Stack

| Technology | Description              |
|------------|--------------------------|
| Java       | Core Project Logic       |
| Java Swing | UI Development           |
| MySQL      | Relational Database      |
| JDBC       | Java-Database Connector  |

---

## 📦 Installation Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/java-emp-management.git
   cd java-emp-management
   ```

2. Open project in **IntelliJ IDEA** / **Eclipse**.

3. Import as a **Java Project**

4. Set up MySQL:
   - Create database:
     ```sql
     CREATE DATABASE emp_db;
     ```
   - Import schema from `db.sql` (if provided).

5. Update your DB credentials in `DBConnection.java`:
   ```java
   String url = "jdbc:mysql://localhost:3306/emp_db";
   String user = "root";
   String password = "yourpassword";
   ```

6. Run the application starting from `Home.java`.

---

## 📁 Folder Structure

```
java_Project(EMP)/
├── src/
│   ├── Home.java
│   ├── OnboardEmployee.java
│   ├── DisplayAll.java
│   ├── SingleEmployee.java
│   ├── UpdateEmployee.java
│   ├── DeBoardEmployee.java
│   └── DBConnection.java
├── db.sql (optional)
└── README.md
```

---

## 🙋‍♂️ Author

**Chandan Kumar Chaurasiya**

- 📫 [LinkedIn](https://www.linkedin.com/in/chandan2004)
- 💼 Portfolio: [chandanchaurasiya.dev](https://chandan-portfolio-tau.vercel.app/)
- 📧 Email: chandan32005c@gmail.com.com

---

## ⭐ Star this repo if it helped you!
