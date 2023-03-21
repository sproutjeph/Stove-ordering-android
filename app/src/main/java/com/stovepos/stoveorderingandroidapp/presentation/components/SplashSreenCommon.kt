package com.stovepos.stoveorderingandroidapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.stovepos.stoveorderingandroidapp.R
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Shapes


@Composable
fun SplashScreenCommon(
    modifier: Modifier = Modifier
        .padding(16.dp),
    imageId: Int = R.drawable.splash_screen_order,
    imageDes: Int,
    title: Int,
    description: Int,
    buttonText: Int = R.string.next_button_text,
    onNextButtonClicked: () -> Unit = {},
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)

    ) {

        Image(
            painter = painterResource(id = imageId),
            contentDescription = stringResource(id = imageDes),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(vertical = 50.dp)

        )




        Column(
            modifier = Modifier
                .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = title),
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.displaySmall.fontSize,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = description),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(30.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = { onNextButtonClicked.invoke() },
                shape = Shapes().extraSmall,
            ) {
                Text(text = stringResource(id = buttonText))
            }

        }


    }

}