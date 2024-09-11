package com.example.plugins

import com.example.route.configureBoard
import com.example.route.configureByeServer
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        configureByeServer()
        configureBoard()
    }
}
