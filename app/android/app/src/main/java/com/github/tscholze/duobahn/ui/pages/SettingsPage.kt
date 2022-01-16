package com.github.tscholze.duobahn.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.tscholze.duobahn.R
import com.github.tscholze.duobahn.data.network.repositories.UnprocessedDataRepository
import com.github.tscholze.duobahn.ui.components.buttons.Chip
import com.google.accompanist.flowlayout.FlowColumn
import org.koin.androidx.compose.get

@Composable
fun SettingsPage(
    repository: UnprocessedDataRepository = get()
) {

    Column(
        modifier = Modifier
            .verticalScroll(state = rememberScrollState())
            .padding(16.dp, 32.dp, 16.dp, 32.dp)
    ) {
        Text(
            stringResource(R.string.settings_choose_autobahns_title),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        FlowColumn {
            repository.getAutobahns().map {
                Chip(name = it.id)
            }
        }
    }
}
