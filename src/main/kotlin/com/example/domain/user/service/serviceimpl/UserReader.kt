package com.example.domain.user.service.serviceimpl

import com.example.domain.user.entity.UserDomain
import org.jetbrains.exposed.sql.transactions.transaction

open class UserReader {
    fun readUser(email: String) : UserDomain {
        return transaction {
            UserDomain.findByEmail(email) ?: throw IllegalArgumentException("존재하지 않는 유저입니다.")
        }
    }
}