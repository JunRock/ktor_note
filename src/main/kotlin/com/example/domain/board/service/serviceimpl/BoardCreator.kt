package com.example.domain.board.service.serviceimpl

import com.example.domain.board.entity.BoardDomain
import com.example.domain.user.service.serviceimpl.UserReader
import com.example.route.BoardRequest
import org.jetbrains.exposed.sql.transactions.transaction

open class BoardCreator(
    private val userReader: UserReader
) {
    fun create(boardRequest: BoardRequest, userId: Int):Int {
        return transaction {
            val user = userReader.readUser(userId)
            BoardDomain.new {
                title = boardRequest.title
                content = boardRequest.content
                this.userId = user.userId
            }.id.value
        }
    }
}