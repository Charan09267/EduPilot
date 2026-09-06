# EduPilotAI 🚀

**AI-Powered Personalized Learning & Career Preparation Platform**

EduPilotAI is a personalized AI-powered learning and career preparation platform built using a **microservices architecture**. It helps users identify skill gaps, build personalized learning roadmaps, discover relevant courses and projects, practice through assessments and mock interviews, and continuously improve their skills.

---

## 🎯 Features

* 🤖 **AI-Powered Learning** – Personalized learning recommendations and career guidance.
* 🗺️ **Personalized Roadmaps** – Generate structured learning roadmaps based on career goals and skills.
* 📚 **Course Discovery** – Discover relevant courses based on learning goals and roadmap requirements.
* 📝 **Assessments** – Take mock tests and other skill-based assessments with evaluation and performance tracking.
* 🎤 **Mock Interviews** – Practice role-specific technical and behavioral interviews.
* 💡 **Recommendations** – Get personalized recommendations on what to learn next.
* 📊 **Progress Tracking** – Track learning and assessment progress over time.

---

## 🏗️ Microservices Architecture

EduPilotAI is divided into independent services, with each service responsible for a specific business capability.

| Service                    | Responsibility                                                                        |
| -------------------------- | ------------------------------------------------------------------------------------- |
| **User Service**           | User registration, authentication, user accounts, and profile management              |
| **Assessment Service**     | Mock tests, skill assessments, test evaluation, and performance tracking              |
| **AI Service**             | AI-powered generation, analysis, personalization, and intelligent learning assistance |
| **Course Service**         | Course management, course discovery, and course-related operations                    |
| **Recommendation Service** | Personalized recommendations for skills, courses, and what to learn next              |
| **Mock Interview Service** | AI-powered mock interviews, interview questions, response evaluation, and feedback    |
| **Roadmap Service**        | Generates and manages personalized learning roadmaps based on user goals and skills   |

---

## 🔄 Platform Flow

```text
                    ┌─────────────────┐
                    │      User       │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │   API Gateway   │
                    └────────┬────────┘
                             │
          ┌──────────────────┼──────────────────┐
          │                  │                  │
          ▼                  ▼                  ▼
   ┌─────────────┐    ┌─────────────┐    ┌─────────────┐
   │ User Service│    │ Assessment  │    │    Course   │
   │             │    │   Service   │    │   Service   │
   └─────────────┘    └─────────────┘    └─────────────┘
          │                  │                  │
          │                  └────────┬─────────┘
          │                           │
          ▼                           ▼
   ┌─────────────┐            ┌──────────────┐
   │ Recommendation│           │  AI Service  │
   │   Service    │            │              │
   └─────────────┘            └──────┬───────┘
                                     │
                         ┌───────────┴───────────┐
                         ▼                       ▼
                  ┌──────────────┐       ┌──────────────┐
                  │ Roadmap      │       │ Mock Interview│
                  │ Service      │       │    Service    │
                  └──────────────┘       └──────────────┘
```

---

## 🛠️ Technology Stack

### Backend

* Java
* Spring Boot
* Spring Security
* JWT
* Spring Cloud
* REST APIs
* Microservices Architecture

### Databases

* MySQL
* MongoDB

### Infrastructure & Communication

* API Gateway
* Service Registry
* Kafka *(for event-driven communication where required)*

### AI

* AI/LLM integration for:

  * Learning roadmap generation
  * Personalized recommendations
  * Mock interview generation
  * Response evaluation
  * Assessment generation and analysis

### Testing & Development

* JUnit
* Postman
* Maven
* Git & GitHub

---

## 🔐 Authentication & Security

EduPilotAI uses **JWT-based authentication** for securing communication between clients and backend services.

The API Gateway validates incoming requests and routes them to the appropriate microservice. Services can additionally validate JWTs for defense-in-depth security.

---

## 📂 Project Structure

```text
EduPilotAI/
│
├── api-gateway/
│
├── service-registry/
│
├── user-service/
│
├── assessment-service/
│
├── ai-service/
│
├── course-service/
│
├── recommendation-service/
│
├── mock-interview-service/
│
├── roadmap-service/
│
├── .gitignore
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:

* Java 17+
* Maven
* MySQL
* MongoDB
* Git

### Clone the Repository

```bash
git clone <https://github.com/Charan09267/EduPilot>
cd EduPilotAI
```

### Build the Services

Navigate to each service and build it using Maven:

```bash
mvn clean install
```

### Run the Services

Start the services individually in the following order:

```text
1. Service Registry
2. API Gateway
3. User Service
4. Assessment Service
5. Course Service
6. AI Service
7. Recommendation Service
8. Roadmap Service
9. Mock Interview Service
```

---

## 🧠 Core Architecture

The platform follows a **microservices architecture** where each service owns a specific business responsibility.

Key architectural principles include:

* Independent and modular services
* REST-based inter-service communication
* Service discovery
* API Gateway for centralized routing
* JWT-based authentication
* Database-per-service approach where appropriate
* Event-driven communication for asynchronous workflows
* Separation of business responsibilities
* Scalable and independently deployable services

---

## 📌 Project Status

🚧 **Currently under active development**

Services are being developed incrementally, starting with the **User Service** and the core microservices infrastructure.

---

## 👨‍💻 Author

**Charan Immati**

B.Tech – Computer Science & Engineering

---

⭐ If you find this project interesting, consider giving the repository a star!
