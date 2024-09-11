package com.example.plugins

import com.example.domain.board.entity.Board
import com.example.domain.user.entity.User
import io.ktor.server.application.*
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    Database.connect(
        url = "jdbc:mysql://localhost:3306/test",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "root"
    )
    transaction {
        SchemaUtils.create(User,Board)
    }
}
