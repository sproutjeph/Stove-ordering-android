package com.stovepos.stoveorderingandroidapp.presentation.screens.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.stovepos.stoveorderingandroidapp.R
import com.stovepos.stoveorderingandroidapp.presentation.components.StoveTopAppBar


@Composable
fun AuthWithEmailScreen(onBackButtonClicked: () -> Unit) {
    Scaffold(
        topBar = {
            StoveTopAppBar(
                title = R.string.empty,
                isMainScreen = false,
                onBackButtonClicked = onBackButtonClicked
            )
        }
    ) { contentPadding->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.stove_full_logo_trans),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(24.dp))


            Text(
                text = stringResource(id = R.string.creeate_account),
                style = MaterialTheme.typography.titleLarge

            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = "",
                onValueChange ={},
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = null)
                },
                label = {
                    Text(text = "Email")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = "",
                onValueChange ={},
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = null)
                },
                label = {
                    Text(text = "Phone Number")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = "",
                onValueChange ={},
                leadingIcon = {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                },
                label = {
                    Text(text = "Name")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = "",
                onValueChange ={},
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Password, contentDescription = null)
                },
                label = {
                    Text(text = "Password")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
//                    navController.navigate(FoodAppScreens.HomeScreen.name)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = Shapes().extraSmall
            ) {
                Text(text = "Sign up")
            }

            Row(
                modifier = Modifier
                    .padding(horizontal =  16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(id = R.string.or_continue),
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(horizontal = 5.dp)

                )

                Divider(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                AuthPro(imageId = R.drawable.google_logo)
                AuthPro(imageId = R.drawable.facebook_logo_1)
                AuthPro(imageId = R.drawable.apple_logo_1)

            }
            Spacer(modifier = Modifier.height(16.dp))
            Row() {
                Text(text = "already have an account")
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Sign in",
                    color = MaterialTheme.colorScheme.primary
                )
            }



        }

    }
}

@Composable
fun AuthPro(
    imageId: Int,
) {
    Box(
        modifier = Modifier
    ){
        Image(
            painter = painterResource(id = imageId),
            contentDescription =  stringResource(id =  R.string.google_text),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = Shapes().extraSmall
                )
                .padding(8.dp)

        )
    }
}