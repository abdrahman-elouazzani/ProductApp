
## 🚀 Tech Stack

* **Kotlin**
* **Jetpack Compose** – UI
* **Coroutines** – async operations
* **Flow** – reactive streams
* **Retrofit** – networking
* **MVVM Architecture**
* **Clean Architecture**
* **Multi-Module Setup**

---

## 🧱 Project Architecture

This project follows **Clean Architecture + MVVM** and is split into multiple modules to ensure **scalability, testability, and separation of concerns**.

```
📦 project-root
 ┣ 📂 app
 ┣ 📂 core
 ┣ 📂 data
 ┗ 📂 domain
```

---

## 📂 Modules Overview

### 🔹 `core` module

Contains common and shared configurations.

* Retrofit setup
* OkHttp configuration
* Network result wrapper

This module is independent and reusable across other modules.

---

### 🔹 `data` module

Responsible for **data handling**.

* Repository implementations
* Remote data sources
* DTOs (Data Transfer Objects)
* API service definitions
* Mappers (DTO → Domain)

Depends on:

```
core
domain
```

---

### 🔹 `domain` module

Contains **business logic** and is completely platform-independent.

* Repository interfaces
* Use cases
* Domain models

This is the **central contract** of the app.


### 🔹 `app` module

Presentation layer.

* Compose UI
* ViewModels
* UI state management

Depends on:

```
domain
```

---

## 🔄 Data Flow

```
UI (Compose)
   ↓
ViewModel
   ↓
UseCase
   ↓
Repository (interface from domain)
   ↓
RepositoryImpl (data module)
   ↓
API Service (Retrofit)
```



## 👨‍💻 Author

**Abdrahman Elouazzani**

Android Developer – Kotlin | Clean Architecture | Compose
