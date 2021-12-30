package com.github.tscholze.duobahn.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.tscholze.duobahn.R
import com.github.tscholze.duobahn.data.network.repositories.UnprocessedDataRepository
import com.google.accompanist.flowlayout.FlowRow
import org.koin.androidx.compose.get


@Composable
fun SettingsPage(
    repository: UnprocessedDataRepository = get()
) {
        Column(
            modifier = Modifier.padding(16.dp, 32.dp, 16.dp, 32.dp)
        ) {
            Text(
                stringResource(R.string.settings_choose_autobahns_title),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            FlowRow {
                repository.getAutobahns().map { 

                    Box(
                        modifier = Modifier.padding(8.dp).background(Color.LightGray)
                    ) {
                        Text(text = it.id)
                    }
                }
            }
        }
    }