package com.github.tscholze.duobahn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.github.tscholze.duobahn.ui.components.structure.DuoBahnApp
import com.github.tscholze.duobahn.ui.theme.DuoBahnTheme
import com.microsoft.device.dualscreen.windowstate.WindowState
import com.microsoft.device.dualscreen.windowstate.rememberWindowState

@ExperimentalFoundationApi
@ExperimentalUnitApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    // MARK: - Private properties -

    private lateinit var windowState: WindowState

    // MARK: - Life cycle -

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            windowState = rememberWindowState()

            DuoBahnTheme {
                DuoBahnApp(windowState)
            }
        }
    }
}