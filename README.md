# Zero Mile Delivery System — Backend (Spring Boot)

This project is a backend service for a logistics company that handles **last-mile parcel delivery** from a central warehouse. It supports parcel creation, file-based order uploads by vendors, and tracking of delivery orders — all built using Spring Boot with DTO-based clean API responses. JWT-based authentication is used for securing endpoints.

---

## 🚀 Features

- ✅ Create a new parcel (single entry)
- ✅ View all parcels / view by tracking ID
- ✅ Vendor registration with subscription type
- ✅ Upload a file of parcel data linked to a delivery order
- ✅ Associate delivery orders with vendors
- ✅ Filter orders by vendor and date
- ✅ View today's delivery orders
- ✅ JWT-based Authentication & Authorization
- ✅ In-memory H2 database
- ✅ Flat, recursive-free API responses using DTOs

---

## ✅ Features Covered in Assignment 3

### 🔐 Role-Based Access Control (RBAC)

Roles implemented:
- `ADMIN`
- `VENDOR`

RBAC is enforced using Spring Security annotations and HTTP method-level restrictions.

---

## 🛠 Tech Stack

- Java 17
- Spring Boot (Web, Data JPA, Security)
- H2 Database (In-memory)
- Maven
- JWT (JSON Web Token)

---

## 📦 API Endpoints (v1)

### 🔐 Authentication

| Method | Endpoint                  | Description             |
|--------|---------------------------|-------------------------|
| POST   | `/api/v1/auth/register`   | Register a new user     |
| POST   | `/api/v1/auth/login`      | Login & receive JWT     |

> 🔑 Use the token received from login as a Bearer Token in the `Authorization` header for all other endpoints.

---

### 👤 Vendors

| Method | Endpoint              | Description                     |
|--------|-----------------------|---------------------------------|
| POST   | `/api/v1/vendors`     | Create a vendor *(auth required)* |
| GET    | `/api/v1/vendors`     | List all vendors (paginated) *(auth required)* |
| GET    | `/api/v1/vendors/{id}`| Get vendor by ID *(auth required)* |

---

### 📦 Parcels

| Method | Endpoint               | Access        | Description          |
|--------|------------------------|---------------|----------------------|
| GET    | `/api/v1/parcels`      | `ADMIN` only  | Fetch all parcels    |
| GET    | `/api/v1/parcels/{id}` | `ADMIN` only  | Fetch parcel by ID   |

> ❌ Vendor has **no access** to parcel APIs.
---

### 📁 Delivery Orders

| Method | Endpoint                          | Access          | Description                            |
|--------|-----------------------------------|-----------------|----------------------------------------|
| POST   | `/api/v1/vendor/upload`           | `VENDOR` only   | Upload delivery order file             |
| GET    | `/api/v1/delivery-orders`         | `ADMIN`, `VENDOR` | Fetch delivery orders by vendor/date  |

> ❌ Admin is **not allowed** to upload delivery order files.
---

### 🌐 Public API

| Method | Endpoint                     | Access | Description                  |
|--------|------------------------------|--------|------------------------------|
| GET    | `/api/v1/track/{trackingId}` | Public | Track parcel by tracking ID |

---

### 📄 Sample Upload File Format

Each line should represent a parcel (CSV-style):

```
Alice, 123 Park Ave, Small, 1.2
Bob, 456 Maple St, Medium, 2.5
```

## 🧪 How to Run Locally
### ✅ Prerequisites
- Java 17+
- Maven

### 🔧 Steps
``` bash
git clone https://github.com/Abhishek-Bansode/tech_eazy_backend_Abhishek-Bansode
```
``` bash
cd tech_eazy_backend_Abhishek-Bansode
```

``` bash
./mvnw spring-boot:run
```

## 🗃️ H2 Database Access
### You can view data in-browser:

 - URL: http://localhost:8080/h2-console

 - JDBC URL: jdbc:h2:mem:testdb

 - Username: sa

 - Password: (leave blank)

### To verify data:

``` sql
SELECT * FROM USER;
SELECT * FROM PARCEL;
SELECT * FROM DELIVERY_ORDER;
SELECT * FROM VENDOR;
```

## 📮 Postman Collection
### The included file zero-mile-delivery.postman_collection.json contains:

* Register / Login
* Create Vendor
* Create Parcel
* Get Parcels
* Upload Delivery Order (file)
* Get Today’s Orders
* Get Orders by Vendor and Date

> Token-based routes need Bearer Token set in the header.

## 🧾 Sample Auth Token Usage
### Header:
```bash
Authorization: Bearer <your-token-here>
```

> ### You can import this into Postman or use the raw JSON in any REST client.

## 📂 Project Structure
``` bash
src/
├── main/
│   ├── java/com/abhishek/techeazy/
│   │   ├── controller/       # REST Controllers
│   │   ├── dto/              # Request/Response DTOs
│   │   ├── entity/           # JPA entities
│   │   ├── repo/             # Spring Data Repositories
│   │   ├── service/          # Business Logic
│   │   ├── config/           # Spring Security Configuration
│   │   ├── security/         # JWT Utility & Filters
│   │   └── ParcelApplication.java
│   └── resources/
│       ├── application.properties
│       └── parcels.txt (sample)
├── zero-mile-delivery.postman_collection.json
└── README.md

```

## 🧰 Sample Credentials
1. to register
    ```json
    {
    "username": "vendor1",
    "password": "vendorpass",
    "role": "VENDOR"
    }
    ```
2. login 
    ```json
    {
    "username": "vendor1",
    "password": "vendorpass"
    }
    ```

> Use /api/v1/auth/register to create your own test users.

