package com.example.domain.board.service

import com.auth0.jwt.interfaces.Payload
import com.example.domain.board.entity.BoardDomain
import com.example.domain.board.service.serviceimpl.BoardCreator
import com.example.domain.board.service.serviceimpl.BoardDeleter
import com.example.domain.board.service.serviceimpl.BoardUpdater
import com.example.route.BoardRequest
import com.example.route.BoardResponse
import com.example.route.toBoardResponse
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class BoardService : KoinComponent {
    private val boardCreator: BoardCreator by inject()
    private val boardUpdater: BoardUpdater by inject()
    private val boardDeleter: BoardDeleter by inject()

    fun createBoard(boardRequest: BoardRequest, payload: Payload): Int {
        println("payloadID = ${payload.id}")
        println("payload.subject = ${payload.subject}")
        println("payload.claims = ${payload.claims}")
        return boardCreator.create(boardRequest, payload.getClaim("email").asString())
    }

    fun getAllBoard(): List<BoardResponse> {
        return transaction {
            BoardDomain.all()
                .map { it.toBoardResponse()}
        }
    }

    fun updateBoard(boardRequest: BoardRequest, boardId: Int): Int {
        return boardUpdater.update(boardRequest, boardId)
    }

    fun deleteBoard(boardId: Int):Int {
        return boardDeleter.delete(boardId)
    }
}
