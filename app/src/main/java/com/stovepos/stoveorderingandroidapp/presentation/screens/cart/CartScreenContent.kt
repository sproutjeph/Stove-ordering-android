package com.stovepos.stoveorderingandroidapp.presentation.screens.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun CartScreenContent(
    totalPrice:Double = 12.00,
    paddingValues: PaddingValues = PaddingValues()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = paddingValues.calculateTopPadding(),
                horizontal = 16.dp
            )

    ){
        item {
            Text(
                text = "CART SUMMARY",
                modifier = Modifier
                    .padding(vertical = 16.dp)

            )

        }



        item {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Subtotal",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )
                Text(
                    text = "$$totalPrice",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )

            }

        }



        item{
            Text(
                text = "CART  (8)",
                modifier = Modifier
                    .padding(vertical = 16.dp)

            )
        }

        items(4){
            CartItem()
        }


    }

}