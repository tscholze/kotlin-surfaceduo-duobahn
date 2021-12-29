package com.github.tscholze.duobahn.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsPage() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            "Hello World",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
    }
}