package com.stovepos.stoveorderingandroidapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stovepos.stoveorderingandroidapp.R
import androidx.compose.foundation.layout.*


@Preview
@Composable
fun FlashCard(
    restaurantName: String = "Restaurant Name",

    ) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal =  16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Green.copy(alpha = 0.4f),
        )


    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
        ) {

            Column(
                modifier = Modifier

            ) {
                Text(
                    text = "Welcome To",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary,

                    )
                Text(
                    text = restaurantName,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary,

                    )
                Text(
                    text ="Menu",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.width(30.dp))


            Image(
                painter = painterResource(id = R.drawable.deliveryman),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
            )

        }
    }

}