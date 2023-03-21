package com.stovepos.stoveorderingandroidapp.presentation.screens.splash_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.stovepos.stoveorderingandroidapp.R
import kotlinx.coroutines.delay


@Composable
fun LogoSplashScreen(
    modifier: Modifier = Modifier,
    navigateToNextSplashScreen: () -> Unit,
) {

    LaunchedEffect(Unit){
        delay(3000L)
        navigateToNextSplashScreen()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Spacer(modifier = Modifier.weight(0.5f))

        Surface() {
            Image(
                painter = painterResource(id = R.drawable.stove_full_logo_trans),
                contentDescription = "Stove Logo",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier

            )
        }



        Spacer(modifier = Modifier.weight(0.5f))

        CircularProgressIndicator(modifier = modifier
            .padding(16.dp)
            .padding(bottom = 50.dp),
            color = MaterialTheme.colorScheme.primary)

    }

}


