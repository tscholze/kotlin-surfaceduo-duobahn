package com.github.tscholze.duobahn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.github.tscholze.duobahn.ui.pages.MapPage
import com.github.tscholze.duobahn.ui.theme.DuoBahnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DuoBahnTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                   MapPage()
                }
            }
        }
    }
}