package com.example.common.koin.submodules

import com.example.auth.JwtProvider
import com.example.auth.PasswordHashProcessor
import org.koin.dsl.module

val authModule = module {
    single {JwtProvider()}
    single { PasswordHashProcessor() }
}