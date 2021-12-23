package com.github.tscholze.duobahn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.tscholze.duobahn.ui.map.MapContent
import com.github.tscholze.duobahn.ui.theme.DuoBahnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DuoBahnTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Box() {
                        MapContent()
                        Text(
                            getString(R.string.app_disclaimer),
                            fontSize = 8.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.TopCenter)
                                .background(Color.White.copy(alpha = 0.5F))
                                .padding(4.dp)
                        )
                    }
                }
            }
        }
    }
}