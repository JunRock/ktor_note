package com.example.common.koin.submodules

import com.example.domain.board.service.BoardService
import com.example.domain.board.service.serviceimpl.BoardCreator
import com.example.domain.board.service.serviceimpl.BoardDeleter
import com.example.domain.board.service.serviceimpl.BoardUpdater
import org.koin.dsl.module

val boardModule = module {
    single { BoardCreator(get()) }
    single { BoardService() }
    single { BoardUpdater() }
    single { BoardDeleter() }
}

