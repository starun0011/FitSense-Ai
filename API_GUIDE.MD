# FitSense-Ai API Guide

This document provides details for all the API endpoints available in the FitSense-Ai project.

## 1. Authentication (`/api/auth`)

### Register User
Create a new user in the system.

- **URL:** `/api/auth/register`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "password": "securepassword",
    "weight": 75
  }
  ```
- **Response:** `200 OK`
  ```json
  {
    "userID": "uuid",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "weight": 75,
    "createdAt": "2024-02-06T10:00:00Z",
    "updatedAt": "2024-02-06T10:00:00Z"
  }
  ```

### Get All Users
List all registered users.

- **URL:** `/api/auth/users`
- **Method:** `GET`
- **Response:** `200 OK`
  ```json
  [
    {
      "userID": "uuid",
      "firstName": "John",
      "lastName": "Doe",
      "email": "john.doe@example.com",
      "weight": 75,
      "createdAt": "...",
      "updatedAt": "..."
    }
  ]
  ```

---

## 2. Activity Tracking (`/api/activity`)

### Create Activity
Log a new fitness activity for a user.

- **URL:** `/api/activity`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "userId": "uuid",
    "type": "RUNNING",
    "duration": 45,
    "effortLevel": "HIGH",
    "startedAt": "2024-02-06T08:00:00Z"
  }
  ```
- **Response:** `200 OK`
  ```json
  {
    "activityId": "uuid",
    "userId": "uuid",
    "type": "RUNNING",
    "startedAt": "2024-02-06T08:00:00Z",
    "duration": 45,
    "effortLevel": "HIGH",
    "caloriesBurned": 525.0,
    "createdAt": "..."
  }
  ```

### Get Activities by User ID
Retrieve all activities logged by a specific user.

- **URL:** `/api/activity/{userId}`
- **Method:** `GET`
- **Response:** `200 OK`
  ```json
  [
    {
      "activityId": "uuid",
      "userId": "uuid",
      "type": "RUNNING",
      "duration": 45,
      "caloriesBurned": 525.0,
      "..."
    }
  ]
  ```

---

## 3. AI Recommendations (`/api/recommendation`)

### Get Recommendation
Get AI-generated fitness recommendations and insights based on a specific activity.

- **URL:** `/api/recommendation`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "activityId": "uuid"
  }
  ```
- **Response:** `200 OK`
  ```json
  {
    "id": "uuid",
    "recommendation": "Great job on your run! To improve performance...",
    "improvements": ["Work on breathing technique", "Increase pace gradually"],
    "safety": ["Stay hydrated", "Stretch after running"],
    "suggestions": ["Try interval training next time"],
    "createdAt": "...",
    "updatedAt": "...",
    "userId": "uuid",
    "activityId": "uuid"
  }
  ```
