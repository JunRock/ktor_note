package com.example

import com.example.common.koin.appModules
import com.example.plugins.configureDatabases
import com.example.plugins.configureRouting
import com.example.plugins.configureSecurity
import com.example.plugins.configureSerialization
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(Koin) {
        modules(appModules)  // Koin 모듈 주입
    }

    configureSerialization()
    configureDatabases()
    configureSecurity()
    configureRouting()
}