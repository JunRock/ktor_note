package com.example.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

fun Application.configureSecurity() {
    install(Authentication) {
        jwt("auth-jwt") {
            realm = "ktor-sample"
            verifier(
                JWT
                    .require(Algorithm.HMAC256("secret")) // JWT 시크릿 키 설정
                    .withAudience("ktor-practice")
                    .withIssuer("JunSeok")
                    .build()
            )

            validate { credential ->
                if (credential.payload.getClaim("ktor-practice")
                        .asString() != ""
                ) JWTPrincipal(credential.payload) else null
            }

            challenge { _, _ ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
            }
        }
    }
}
