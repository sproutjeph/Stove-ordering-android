package com.stovepos.stoveorderingandroidapp.presentation.screens.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.stovepos.stoveorderingandroidapp.R
import com.stovepos.stoveorderingandroidapp.presentation.components.AuthProviderButton

@Composable
fun AuthScreenContent(
    loadingState: Boolean,
    navigateToAuthWithEmail: () -> Unit,
    contentPadding:PaddingValues,
    onButtonClicked: () -> Unit

) {
    Column(
        modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {


        Image(
            painter = painterResource(id = R.drawable.splash_screen_login),
            contentDescription = "null",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(250.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.register_login_text),
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = MaterialTheme.colorScheme.primary
            )

            AuthProviderButton(
                loadingState = loadingState,
                primaryText = stringResource(id = R.string.google_text),
                icon = R.drawable.google_logo,
                onClick = onButtonClicked
            )



            AuthProviderButton(
                primaryText = stringResource(id = R.string.apple_text),
                icon = R.drawable.apple_logo_1
            ) {

            }

            AuthProviderButton(
                primaryText = stringResource(id = R.string.facebook_text),
                icon = R.drawable.facebook_logo_1
            ) {

            }



        }

        Row(
            modifier = Modifier
                .padding(horizontal =  16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.or_text),
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(horizontal = 5.dp)

            )

            Divider(modifier = Modifier.weight(1f))
        }

        Button(
            onClick = {
                navigateToAuthWithEmail.invoke()
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = Shapes().extraSmall


        ) {
            Text(
                text = stringResource(id = R.string.signin_email)
            )
        }

        Row() {
            Text(text = "Don't have an account ")
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Sign up",
                color = MaterialTheme.colorScheme.primary
            )
        }



    }


}