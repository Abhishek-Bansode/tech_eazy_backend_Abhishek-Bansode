# Zero Mile Delivery System — Backend (Spring Boot)

This project is a backend service for a logistics company that handles **last-mile parcel delivery** from a central warehouse. The system allows parcel creation, retrieval by tracking ID, and listing all parcels. It uses an in-memory H2 database and follows a minimal, RESTful API design with Spring Boot.

---

## 🚀 Features

- ✅ Create a new parcel with customer and delivery details
- ✅ View all parcels received at the warehouse
- ✅ Retrieve a parcel using a unique tracking ID
- ✅ In-memory storage using H2
- ✅ Clean and minimal Spring Boot setup

---

## 🛠 Tech Stack

- Java 17
- Spring Boot (Web, Data JPA)
- H2 Database (In-memory)
- Maven

---

## 📦 API Endpoints

### 1. ➕ Create a Parcel
**POST** `/api/v1/parcels`

#### Sample Request Body:
```json
{
  "customerName": "John Doe",
  "deliveryAddress": "123 Main St",
  "contactNumber": "9876543210",
  "size": "Medium",
  "weight": 2.5
}
```

### 2. 📋 Get All Parcels
**GET** `/api/v1/parcels`

#### Returns a list of all stored parcels.

### 3. 🔍 Get Parcel by Tracking ID
**GET** `/api/v1/parcels/{id}`

#### Replace {id} with the actual parcel ID received from creation or listing.

## 🧪 How to Run Locally
### ✅ Prerequisites
- Java 17+
- Maven

### 🔧 Steps
``` bash
git clone https://github.com/Abhishek-Bansode/tech_eazy_backend_Abhishek-Bansode
cd tech_eazy_backend_Abhishek-Bansode
./mvnw spring-boot:run

```
## 🗃️ H2 Database Access
### You can view data in-browser:

 - URL: http://localhost:8080/h2-console

 - JDBC URL: jdbc:h2:mem:testdb

 - Username: sa

 - Password: (leave blank)

### Run:

``` sql
SELECT * FROM PARCEL;
```

## 📮 Postman Collection
### The included file zero-mile-delivery.postman_collection.json contains:

1. Create Parcel (POST)

2. Get All Parcels (GET)

3. Get Parcel by ID (GET)

#### You can import this into Postman or use the raw JSON in any REST client.

## 📂 Project Structure
``` bash

src/
├── main/
│   ├── java/com/example/parcel/
│   │   ├── controller/       # REST APIs
│   │   ├── dto/              # Request objects
│   │   ├── entity/           # JPA entities
│   │   ├── repo/             # Repositories
│   │   ├── service/          # Business logic
│   │   └── ParcelApplication.java
│   └── resources/
│       └── application.properties
├── zero-mile-delivery.postman_collection.json
└── README.md
```
