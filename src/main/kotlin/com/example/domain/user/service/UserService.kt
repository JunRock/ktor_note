package com.example.domain.user.service

import com.example.domain.user.entity.User
import com.example.domain.user.entity.UserDTO
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

open class UserService {
    fun createUser(userDTO: UserDTO) {
        transaction {
            User.insert {
                it[username] = userDTO.username
                it[age] = userDTO.age
            }
        }
    }
}