package com.example.route

import com.example.domain.board.entity.BoardDomain
import com.example.domain.board.service.BoardService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

fun Route.configureBoard() {
    val boardService by inject<BoardService>()
    route("/api/board") {
        authenticate("auth-jwt") {
            post {
                val boardRequest = call.receive<BoardRequest>()
                val principal = call.authentication.principal<JWTPrincipal>()!!
                val createBoard = boardService.createBoard(boardRequest, principal.payload)
                call.respond(HttpStatusCode.OK, createBoard)
            }
        }

        get("/all") {
            call.respond(HttpStatusCode.OK,boardService.getAllBoard())
        }

        patch("/{id}") {
            val boardRequest = call.receive<BoardRequest>()
            val boardId = call.parameters["id"]?.toInt()
            call.respond(HttpStatusCode.OK,boardService.updateBoard(boardRequest,boardId!!))
        }

        delete("/{id}") {
            val boardId = call.parameters["id"]?.toInt()
            call.respond(HttpStatusCode.OK,boardService.deleteBoard(boardId!!))
        }
    }
}


@Serializable
data class BoardRequest(val title: String, val content: String)

@Serializable
data class BoardResponse(
    val title: String,
    val content: String,
    val userId: Int
)

fun BoardDomain.toBoardResponse() = BoardResponse(
    title = this.title,
    content = this.content,
    userId = this.userId.value
)