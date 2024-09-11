package com.example.domain.board.service.serviceimpl

import com.example.domain.board.entity.Board
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction

open class BoardDeleter {
    fun delete(boardId: Int): Int{
        return transaction {
            Board.deleteWhere { Board.id eq boardId }
            boardId
        }
    }
}