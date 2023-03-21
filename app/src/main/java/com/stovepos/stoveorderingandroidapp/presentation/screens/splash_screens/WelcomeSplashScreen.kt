package com.stovepos.stoveorderingandroidapp.presentation.screens.splash_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.stovepos.stoveorderingandroidapp.R
import kotlinx.coroutines.delay

@Composable
fun WelcomeSplashScreen(
    modifier: Modifier = Modifier
        .padding(16.dp)
        .background(MaterialTheme.colorScheme.surface),
    navigateToNextSplashScreen: () -> Unit

) {
    val isDarkTheme = isSystemInDarkTheme()

    LaunchedEffect(Unit){
        delay(3000L)
        navigateToNextSplashScreen()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.item_image),
            contentDescription = "null",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),


        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "Welcome to \uD83D\uDC4B",
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.displaySmall.fontSize,
                color = Color.White
            )

            Text(
                text = "Stove Ordering App",
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.displaySmall.fontSize,
                color = Color.White

            )
            Spacer(modifier = Modifier.height(12.dp))


        }

        Text(
            text = stringResource(id = R.string.welcome_splash_screen_text),
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal =  40.dp, vertical = 60.dp),
            fontSize = MaterialTheme.typography.titleLarge.fontSize,


            )


    }

}

