# News Management System

## Описание

News Management System — это RESTful приложение для управления новостями и комментариями. Оно предоставляет возможности для создания, редактирования, удаления и просмотра новостей, а также добавления комментариев. В проекте реализована ролевой модель доступа с различными уровнями прав (admin, journalist, subscriber) и обработка исключений в REST-формате.

Проект реализован с использованием **Java**, **Spring Boot**, и базы данных **PostgreSQL**. Также поддерживаются другие базы данных, такие как **H2** для тестового режима.

## Технологии

- **Java 11+**
- **Spring Boot**
- **Spring Security** для аутентификации и авторизации
- **Spring Data JPA** для работы с базой данных
- **PostgreSQL** или **H2**
- **Maven** для управления зависимостями

## Установка и запуск

### Шаги для запуска проекта:

1. **Клонирование репозитория**
    ```bash
    git clone <URL репозитория>
    cd news-management-system
    ```

2. **Настройка базы данных**
    - Убедитесь, что у вас установлен **PostgreSQL**.
    - Создайте базу данных:
      ```sql
      CREATE DATABASE news_management;
      ```

3. **Настройка параметров подключения к базе данных**
    - Откройте файл `src/main/resources/application.yml` и измените настройки для подключения к базе данных:
      ```yaml
      spring:
        datasource:
          url: jdbc:postgresql://localhost:5432/news_management
          username: <Ваш пользователь БД>
          password: <Ваш пароль БД>
          driver-class-name: org.postgresql.Driver

        jpa:
          hibernate:
            ddl-auto: update
          show-sql: true
          properties:
            hibernate:
              dialect: org.hibernate.dialect.PostgreSQLDialect
      ```

4. **Сборка и запуск проекта**
    - Выполните следующие команды для сборки и запуска проекта:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

5. **Доступ к API**
    - API будет доступно по адресу:
      ```
      http://localhost:8080
      ```

## API Эндпоинты

### 1. **CRUD для новостей**
- **Создание новости:** `POST /api/news`
- **Просмотр новости:** `GET /api/news/{id}`
- **Обновление новости:** `PUT /api/news/{id}`
- **Удаление новости:** `DELETE /api/news/{id}`

### 2. **CRUD для комментариев**
- **Создание комментария:** `POST /api/comments`
- **Просмотр комментария:** `GET /api/comments/{id}`
- **Обновление комментария:** `PUT /api/comments/{id}`
- **Удаление комментария:** `DELETE /api/comments/{id}`

### 3. **Пагинация новостей**
- **Просмотр списка новостей:** `GET /api/news?page=0&size=10`

### 4. **Поиск новостей**
- **Полнотекстовый поиск:** `GET /api/news/search?query=<search_term>`

## Роли пользователей

- **Admin:** Полный доступ к CRUD-операциям.
- **Journalist:** Может управлять только своими новостями.
- **Subscriber:** Может управлять только своими комментариями.
- **Гость:** Может просматривать новости и комментарии.

## Структура проекта

- `src/main/java/com/newsmanagement/`
    - `controller/` — контроллеры для обработки запросов.
    - `entity/` — сущности базы данных.
    - `repository/` — репозитории для работы с данными.
    - `service/` — бизнес-логика приложения.
    - `config/` — конфигурация безопасности и базы данных.

- `src/main/resources/`
    - `application.yml` — настройки для базы данных и приложения.

## Тестирование

Для запуска тестов выполните следующую команду:

```bash
mvn test