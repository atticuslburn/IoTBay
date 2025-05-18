# 🛒 IoTBay Web App - 41025 Introduction to Software Development

A basic JSP + Java web application that simulates an IoT shopping platform. Customers can browse smart devices, while admin users can manage items and user data.

## 📦 Features

- Browse a list of IoT items in a grid layout
- User login, register, logout
- Role-based access (admin / customer)
- Styled using custom CSS and CSS Grid
- SQLite database with test data via `initialise.sql`

## 🛠 Tech Stack

- Java (Servlets)
- JSP / HTML / CSS
- SQLite Database
- Apache Tomcat 10
- IntelliJ IDEA

---

## 🚀 How to Run

1. Clone the repo
2. Open in IntelliJ IDEA
3. Deploy on Tomcat server
4. Run `initialise.sql` in SQLite
5. Run the deployed Tomcat
6. Visit `http://localhost:9090/`

---

## 🗃 Sample Users

| Email             | Password | Role     |
|------------------|----------|----------|
| John@citizen     | abc      | customer |
| bruh@gmail       | abc      | admin    |
| yoho@fake        | abc      | customer |
| jafdnjnfds@fzdnjfds | abc   | admin    |

> Additional user permissions (e.g. isAdmin, isMerchant) are defined in `USERPERMISSIONS`.

---

## 🧑‍💼 Sample Customers

| Name           | Type        | Email                       |
|----------------|-------------|-----------------------------|
| Alice Smith    | individual  | alice.smith@example.com     |
| Delta Co.      | company     | contact@deltaco.com         |
| Ivy Innovations| company     | hello@ivyinnov.com          |
| Mia Patel      | individual  | mia.patel@example.com       |
| Quinn Enterprises | company  | hello@quinnent.com          |

> A total of 20 customers (individual + company) are preloaded via `initialise.sql`.

---

## 📁 Image Naming Convention

Images go in:

/webapp/images/items/

They are auto-mapped from the item name:
> Example:  
> `Apple AirPods Pro` → `apple_airpods_pro.jpg`

---

## 🧾 Database Tables

- `USERS` — login, registration, roles
- `USERPERMISSIONS` — isAdmin, isMerchant, isRegistered
- `ITEMS` — IoT item catalog
- `ORDERS` — user purchases (structure ready)
- `user_access_log` — login and logout tracking
- `customers` — external user/customer info (name, email, type, address)

---

## 🧑‍💻 Developed By

This web application was developed as part of Assignment 1 & 2 for 41025 Introduction to Software Development.

Group 4 Members:
- Adrian Irwin (24527172)
- Atticus Burn (24793022)
- Esha Sadarangani (24461093)
- Nima Nadali (14503407)
- Sanchit Khosla (24607537)
- Yu-Han Chang (John) (14542423)

---

## ✅ Notes

- Purchase function is not fully implemented
- Images are required to match item names
- Layout is responsive and styled simply
