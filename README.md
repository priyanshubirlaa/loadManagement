# Load Management API - Spring Boot

## Overview
This Spring Boot project provides a RESTful API for managing loads. It includes CRUD operations to create, retrieve, update, and delete loads. The API also integrates Swagger for API documentation and exception handling for better error management.

## API Endpoints

### 1. Create a Load
**Endpoint:** `POST /load`

**Payload:**
```json
{
  "facility": {
    "loadingPoint": "delhi",
    "unloadingPoint": "jaipur",
    "loadingDate": "dd-mm-yyyy",
    "unloadingDate": "dd-mm-yyyy"
  },
  "productType": "chemicals",
  "truckType": "canter",
  "noOfTrucks": "1",
  "weight": "100",
  "comment": "",
  "shipperId": "shipper:<UUID>",
  "date": "dd-mm-yyyy"
}
```

**Response:** `201 Created`
```json
{
  "loadId": "load:<UUID>",
  "facility": {
    "loadingPoint": "delhi",
    "unloadingPoint": "jaipur",
    "loadingDate": "dd-mm-yyyy",
    "unloadingDate": "dd-mm-yyyy"
  },
  "productType": "chemicals",
  "truckType": "canter",
  "noOfTrucks": "1",
  "weight": "100",
  "comment": "",
  "shipperId": "shipper:<UUID>",
  "date": "dd-mm-yyyy"
}
```

### 2. Retrieve Loads
**Endpoint:** `GET /load`

**Query Parameters:**
- `shipperId`
- `truckType`
- `productType`
- `loadingPoint`
- `unloadingPoint`

**Response:** List of loads matching the query parameters.

### 3. Retrieve Load by ID
**Endpoint:** `GET /load/{loadId}`

**Response:** Returns the load details.

### 4. Update Load
**Endpoint:** `PUT /load/{loadId}`

**Payload:**
```json
{
  "facility": {
    "loadingPoint": "Delhi",
    "unloadingPoint": "Mumbai",
    "loadingDate": "19-03-2025",
    "unloadingDate": "20-03-2025"
  },
  "productType": "electronics",
  "truckType": "trailer",
  "noOfTrucks": "2",
  "weight": "200",
  "comment": "Updated load",
  "shipperId": "shipper:123e4567-e89b-12d3-a456-426614174000",
  "date": "17-03-2025"
}
```

**Response:**
```json
{
  "loadId": "3e2cc027-67c3-44c7-a466-9685acc95c28",
  "facility": {
    "loadingPoint": "Delhi",
    "unloadingPoint": "Mumbai",
    "loadingDate": "19-03-2025",
    "unloadingDate": "20-03-2025"
  },
  "productType": "electronics",
  "truckType": "trailer",
  "noOfTrucks": "2",
  "weight": "200",
  "comment": "Updated load",
  "shipperId": "shipper:123e4567-e89b-12d3-a456-426614174000",
  "date": "17-03-2025"
}
```

### 5. Delete Load
**Endpoint:** `DELETE /load/{loadId}`

**Response:** `200 OK` if deleted successfully.

## Swagger API Documentation
This project includes Swagger integration to visualize and interact with API endpoints. You can access the Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```

## Exception Handling
Custom exception handling is implemented to manage invalid requests, missing resources, and server errors efficiently.

## Database Configuration
This project uses PostgreSQL hosted on Railway. Update your `application.properties` with the following configuration:

```properties
spring.application.name=loadManagement
spring.datasource.url=jdbc:postgresql://<DATABASE_URL>:<PORT>/<DATABASE_NAME>
spring.datasource.username=<USERNAME>
spring.datasource.password=<PASSWORD>
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```

Replace placeholders (`<DATABASE_URL>`, `<PORT>`, `<DATABASE_NAME>`, `<USERNAME>`, `<PASSWORD>`) with your actual database credentials.

## Setup Instructions
1. Clone the repository:
   ```sh
   git clone https://github.com/priyanshubirlaa/loadManagement
   ```
2. Navigate to the project directory:
   ```sh
   cd spring-boot-load-api
   ```
3. Build and run the project:
   ```sh
   mvn spring-boot:run
   ```


