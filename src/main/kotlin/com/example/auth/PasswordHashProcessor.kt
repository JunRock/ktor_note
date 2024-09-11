package com.example.auth

import at.favre.lib.crypto.bcrypt.BCrypt

class PasswordHashProcessor {
    fun hashPassword(password: String): String {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray()) // bcrypt로 비밀번호 해싱
    }

    fun verifyPassword(password: String, hashedPassword: String) {
        if(!BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified){
            throw IllegalStateException("회원정보가 일치하지 않습니다!")
        }
    }
}