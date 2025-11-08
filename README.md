# Courses - Поиск и подбор курсов 🎓
<img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android"> <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin">

Мобильное приложение для поиска, фильтрации и сохранения образовательных курсов. Многомодульное Android-приложение с современной архитектурой.

## 🚀 Технологии и стек

### 📱 Основные технологии
- **Kotlin** + **Coroutines** + **Flow**
- **Multi-module architecture** (Clean Architecture)
- **Koin** - dependency injection
- **Retrofit** - сетевые запросы
- **Room** - локальная база данных
- **Navigation Component** - навигация
- **ViewBinding** - привязка view

### 🏗 Архитектура
```text
app/ # Главный модуль приложения
core/ # Общие утилиты и базовые классы
data/ # Data layer (репозитории, API, DB)
domain/ # Domain layer (use cases, entities)
features/ # Feature modules:
├── main/ # Главный экран и навигация
├── auth/ # Авторизация
├── home/ # Домашний экран
├── course/ # Детали курса
├── favorites/ # Избранные курсы
└── account/ # Личный кабинет
```
## 📦 Модули

### 🎯 App Module
- Точка входа приложения
- Главный AndroidManifest

### 🔧 Core Module
- Общие ресурсы
- Базовые классы
- Утилиты

### 💾 Data Module
- Retrofit API clients
- Room database
- Repository implementations
- Data sources

### 🧠 Domain Module
- Use cases
- Repository interfaces
- Domain models
- Business logic

### 🎨 Feature Modules
Каждый feature модуль содержит:
- layout
- UI компоненты (Fragment, Activity)
- ViewModel
- DI модуль
- строковые ресурсы

## 🛠 Установка и запуск

### Предварительные требования
- Android Studio
- Android SDK 26+
- Java 17

### Сборка проекта
```bash
# Клонировать репозиторий
git clone https://github.com/your-username/testcourses.git

# Открыть в Android Studio
# Дождаться синхронизации Gradle
# Запустить на устройстве/эмуляторе
```
## 🏗 Архитектурные принципы

### Clean Architecture
*   Четкое разделение слоев

*   Независимость от фреймворков

*   Тестируемость компонентов

MVVM + Repository Pattern
```text
UI Layer (Fragment/Activity) 
    ↓
ViewModel 
    ↓
Use Cases 
    ↓
Repository 
    ↓
Data Sources (API, DB)
```
### Dependency Injection
*   Koin для управления зависимостями

*   Модульная структура DI

*   Легкое тестирование

  ⭐ Поставьте звезду репозиторию, если проект вам понравился!
