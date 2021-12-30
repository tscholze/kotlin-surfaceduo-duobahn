package com.github.tscholze.duobahn.ui.pages

import android.annotation.SuppressLint
import android.os.CountDownTimer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.tscholze.duobahn.R
import com.github.tscholze.duobahn.data.network.dto.Autobahn
import com.github.tscholze.duobahn.data.network.repositories.UnprocessedDataRepository
import com.github.tscholze.duobahn.ui.theme.AutobahnBlue
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

/**
 * The PreparationPage will be shown wether the app
 * requests required data from the server or if an
 * app-rebuild is necessary.
 *
 * @param navController: App-wide navigation controller.
 */
@Composable
fun PreparationPage(navController: NavController, repository: UnprocessedDataRepository = get()) {

    val coroutineScope = rememberCoroutineScope()
    val autobahns = remember { mutableListOf<Autobahn>() }


    LaunchedEffect(navController, repository) {
        coroutineScope.launch {
            repository.fetchAutobahns { navController.navigate("map") }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()

    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(R.string.misc_logo_description),
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.height(125.dp)
        )

        // Loading indicator.
        LinearProgressIndicator(
            color = AutobahnBlue,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Loading text.
        Text("Preparing app data set. Please be patient.")
    }
}

private fun requestData(navController: NavController) {

}
