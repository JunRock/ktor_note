package com.example.domain.user.entity

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable


object User: IntIdTable() {
    val username = varchar("username", 20)
    val email = varchar("email", 70)
    val password = varchar("password", 100)
}
@Serializable
data class UserDTO(
    val username: String,
    val email: String,
    val password: String
)

class UserDomain(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserDomain>(User){
        fun findByUsername(email: String): UserDomain? {
            return UserDomain.find { User.email eq email }.firstOrNull()
        }
    }  // User 테이블과 연결

    var userId by User.id  // User 테이블의 컬럼
    var username by User.username
    var email by User.email
    var password by User.password
}
