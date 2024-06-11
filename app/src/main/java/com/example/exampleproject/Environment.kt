package com.example.exampleproject

enum class Environment (val apiUrl: String, val databaseUrl: String, val loggingLevel: String) {
    DEV (
        apiUrl = "https://dev-api.example.com",
        databaseUrl = "jdbc:mysql://dev-db.example.com:3306/dev_db",
        loggingLevel = "DEBUG"
    ),
    SIT(
        apiUrl = "https://sit-api.example.com",
        databaseUrl = "jdbc:mysql://sit-db.example.com:3306/sit_db",
        loggingLevel = "INFO"
    ),
    UAT(
        apiUrl = "https://uat-api.example.com",
        databaseUrl = "jdbc:mysql://uat-db.example.com:3306/uat_db",
        loggingLevel = "INFO"
    ),
    PROD(
        apiUrl = "https://api.example.com",
        databaseUrl = "jdbc:mysql://db.example.com:3306/prod_db",
        loggingLevel = "WARN"
    )
}