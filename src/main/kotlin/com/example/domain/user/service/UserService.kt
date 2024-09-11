package com.example.domain.user.service

import com.example.auth.JwtProvider
import com.example.auth.PasswordHashProcessor
import com.example.auth.dto.request.LoginRequest
import com.example.auth.dto.response.TokenResponse
import com.example.domain.user.entity.UserDTO
import com.example.domain.user.entity.UserDomain
import org.jetbrains.exposed.sql.transactions.transaction

open class UserService(
    private val passwordHashProcessor: PasswordHashProcessor,
    private val jwtProvider: JwtProvider
) {
    fun createUser(userDTO: UserDTO) {
        val hashPassword = passwordHashProcessor.hashPassword(userDTO.password)
        transaction {
            UserDomain.new {
                username = userDTO.username
                password = hashPassword
                email = userDTO.email
            }
        }
    }

    fun loginUser(loginRequest: LoginRequest): TokenResponse {
        transaction {
            val user = UserDomain.findByUsername(loginRequest.email)
            passwordHashProcessor.verifyPassword(loginRequest.password,user!!.password)
        }
        return TokenResponse(
            accessToken = jwtProvider.generateToken(loginRequest.email)
        )
    }
}