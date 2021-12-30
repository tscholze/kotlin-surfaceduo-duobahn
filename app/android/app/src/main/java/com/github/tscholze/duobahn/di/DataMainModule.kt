package com.github.tscholze.duobahn.di

import com.github.tscholze.duobahn.data.network.repositories.UnprocessedDataRepository
import org.koin.dsl.module

/**
 * Koin Module common to all build flavors
 */
val dataMainModule = module {
    single { UnprocessedDataRepository() }
}