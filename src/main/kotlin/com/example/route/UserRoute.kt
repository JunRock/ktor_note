package com.example.route

import com.example.auth.dto.request.LoginRequest
import com.example.domain.user.entity.UserDTO
import com.example.domain.user.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.configureByeServer(){
    val service by inject<UserService>()
    route("/api/user"){
        post{
            val user = call.receive<UserDTO>()
            service.createUser(user)
            call.respond(HttpStatusCode.Created)
        }

        post("/login"){
            val user = call.receive<LoginRequest>()
            val jwtToken = service.loginUser(user)
            call.respond(HttpStatusCode.OK,jwtToken)
        }
    }
}