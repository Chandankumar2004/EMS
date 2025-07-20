
<h1 align="center">🧑‍💼 Employee Management System</h1>
<h3 align="center"><code>(Java + Swing + MySQL)</code></h3>

<p align="center">
  A fully functional <strong>Employee Management System</strong> built using Java Swing for GUI and MySQL for database operations.  
</p>

---

## 📸 Project Screenshots

> All images are stored in the `projectEMSImg/` folder:

### 1. 🏠 Home Screen  
![Home Screen](https://github.com/Chandankumar2004/EMS/blob/b583813093e156f52889124f6021a5d0d49458d5/home.png)

---

### 2. ➕ Onboard Employee  
![Onboard Employee](https://github.com/Chandankumar2004/EMS/blob/b583813093e156f52889124f6021a5d0d49458d5/onboard%20new.png)

---

### 3. 📋 View All Employees  
![View All](https://github.com/Chandankumar2004/EMS/blob/b583813093e156f52889124f6021a5d0d49458d5/view%20All.png)

---

### 4. 🔍 Search Employee  
![Search](https://github.com/Chandankumar2004/EMS/blob/b583813093e156f52889124f6021a5d0d49458d5/search.png)

---

### 5. ✏️ Update Employee  
#### a. Enter ID to Update  
![Update Step 1](https://github.com/Chandankumar2004/EMS/blob/b583813093e156f52889124f6021a5d0d49458d5/enterIdToUpdate.png)

#### b. Edit & Save Details  
![Update Step 2](https://github.com/Chandankumar2004/EMS/blob/b583813093e156f52889124f6021a5d0d49458d5/editAndSaveDetails.png)

---

### 6. ❌ De-board Employee  
![DeBoard](https://github.com/Chandankumar2004/EMS/blob/b583813093e156f52889124f6021a5d0d49458d5/delete.png)

---

## 🚀 Features

✅ Add New Employee  
✅ View All Employees  
✅ Search by Employee ID  
✅ Update Employee Record  
✅ Delete/De-board Employee  
✅ User-friendly GUI with Java Swing  
✅ Database-backed operations using MySQL  

---

## 🛠 Tech Stack

| Technology | Role |
|------------|------|
| Java | Core Application Logic |
| Java Swing | User Interface |
| MySQL | Data Persistence |
| JDBC | Database Connectivity |

---

## 📦 How to Run Locally

```bash
# 1. Clone the repository
git clone https://github.com/Chandankumar2004/EMS.git
cd EMS

# 2. Open in your Java IDE (IntelliJ / Eclipse)
# 3. Ensure MySQL is running and create the database:

CREATE DATABASE emp_db;

# 4. Update database credentials in DBConnection.java:

String url = "jdbc:mysql://localhost:3306/emp_db";
String user = "root";
String password = "yourpassword";

# 5. Run Home.java to launch the system
```

---

## 📁 Project Structure

```
EMS/
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
🔗 [LinkedIn](https://www.linkedin.com/in/chandan2004)  
🌐 [Portfolio](https://chandan-portfolio-tau.vercel.app/)  
📧 Email: chandan32005c@gmail.com

---

> ⭐ **Star** this repository to support the project and help others discover it!
