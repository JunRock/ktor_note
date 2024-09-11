package com.example.domain.user.service.serviceimpl

import com.example.domain.user.entity.UserDomain
import org.jetbrains.exposed.sql.transactions.transaction

open class UserReader {
    fun readUser(userId: Int) : UserDomain {
        return transaction {
            UserDomain.findById(userId) ?: throw IllegalArgumentException("존재하지 않는 유저입니다.")
        }
    }
}