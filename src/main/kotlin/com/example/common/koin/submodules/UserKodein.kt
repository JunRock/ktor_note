package com.example.common.koin.submodules

import com.example.domain.user.service.UserService
import com.example.domain.user.service.serviceimpl.UserReader
import org.koin.dsl.module


val userModule = module {
    single { UserService(get(),get()) }
    single { UserReader() }
}

