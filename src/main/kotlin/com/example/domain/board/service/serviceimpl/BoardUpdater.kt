package com.example.domain.board.service.serviceimpl

import com.example.domain.board.entity.BoardDomain
import com.example.route.BoardRequest
import org.jetbrains.exposed.sql.transactions.transaction

open class BoardUpdater {
    fun update(boardRequest: BoardRequest, boardId: Int): Int {
        return transaction {
            val board = BoardDomain.findById(boardId) ?: throw IllegalArgumentException("Board not found")
            board.title = boardRequest.title
            board.content = boardRequest.content
            board.id.value
        }
    }
}