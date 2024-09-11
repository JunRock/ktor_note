package com.example.domain.board.entity

import com.example.domain.user.entity.User
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Board : IntIdTable() {
    val title = varchar("title", 255)
    val content = varchar("content", 255)
    val userId = reference("user_id", User)
}

class BoardDomain(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BoardDomain>(Board)

    var title by Board.title
    var content by Board.content
    var userId by Board.userId
}