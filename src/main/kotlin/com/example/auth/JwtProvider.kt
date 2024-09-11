package com.example.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

class JwtProvider {
    fun generateToken(username: String): String {
        val jwtAudience = "ktor-practice"
        return JWT.create()
            .withAudience(jwtAudience)
            .withIssuer("JunSeok")
            .withClaim("username", username)
            .withExpiresAt(Date(System.currentTimeMillis() + 60000)) // 1분간 유효
            .sign(Algorithm.HMAC256("secret"))
    }
}