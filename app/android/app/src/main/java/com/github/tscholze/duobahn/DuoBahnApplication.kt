package com.github.tscholze.duobahn

import android.app.Application
import com.github.tscholze.duobahn.di.dataMainModule
import org.koin.core.context.GlobalContext.startKoin
import org.koin.android.ext.koin.androidContext

/*
 * This is required for Koin.
 *
 * If you are looking for the composable app wrapper
 * have a look at `DuoBahnApp` function.
 */
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