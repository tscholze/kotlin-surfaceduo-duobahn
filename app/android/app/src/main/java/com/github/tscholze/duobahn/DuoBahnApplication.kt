package com.github.tscholze.duobahn

import android.app.Application
import com.github.tscholze.duobahn.di.dataMainModule
import org.koin.core.context.GlobalContext.startKoin
import org.koin.android.ext.koin.androidContext

class DuoBahnApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DuoBahnApplication )
            modules(
                dataMainModule
            )
        }
    }
}