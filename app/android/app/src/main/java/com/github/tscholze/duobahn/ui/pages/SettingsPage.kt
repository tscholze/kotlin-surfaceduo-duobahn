package com.github.tscholze.duobahn.ui.pages

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
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
import com.google.accompanist.flowlayout.FlowColumn
import org.koin.androidx.compose.get

@Composable
fun SettingsPage(
    repository: UnprocessedDataRepository = get()
) {

    Column(
        modifier = Modifier
            .verticalScroll(state = rememberScrollState())
            .padding(top = 32.dp)
            .heightIn(max = 500.dp)
    ) {
        Text(
            stringResource(R.string.settings_choose_autobahns_title),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        FlowColumn(
            modifier = Modifier
                .horizontalScroll(state = rememberScrollState())
                .padding(top = 8.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        ) {
            repository.getAutobahns().map {
                Text(it.name)
            }
        }
    }
}
