package com.stovepos.stoveorderingandroidapp.presentation.screens.my_payment_cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.stovepos.stoveorderingandroidapp.R
import com.stovepos.stoveorderingandroidapp.presentation.components.StoveTopAppBar

@Composable
fun MyPaymentCardsScreen(
    onBackButtonClicked: () -> Unit = {},
) {


    Scaffold(
        topBar = {
            StoveTopAppBar(
                title =  R.string.my_payment_cards,
                onBackButtonClicked = onBackButtonClicked,
                isMainScreen = false
            )
        },
    ) { paddingValues ->
        MyPaymentsCardsScreenContent(paddingValues = paddingValues)



    }

}

@Composable
fun MyPaymentsCardsScreenContent(
    paddingValues: PaddingValues,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        CardDataField(
            paymentType = "Google Pay",
            cardProviderImage = R.drawable.google_logo
        )

        Spacer(modifier = Modifier.height(16.dp))
        CardDataField(
            paymentType = "Apple Pay",
            cardProviderImage = R.drawable.apple_logo_1

        )

        Spacer(modifier = Modifier.height(16.dp))
        CardDataField(
            paymentType = ".... .... .... 4679",
            cardProviderImage = R.drawable.mastercard

        )
        Spacer(modifier = Modifier.height(16.dp))
        CardDataField(
            paymentType = ".... .... .... 4679",
            cardProviderImage = R.drawable.visa

        )

        Spacer(modifier = Modifier.height(16.dp))
        CardDataField(
            paymentType = ".... .... .... 2766",
            cardProviderImage = R.drawable.mastercard

        )

        Spacer(modifier = Modifier.height(16.dp))




        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(16.dp),
        ) {
            Text(text = "Add New Card")
        }
    }



}


@Composable
fun CardDataField(
    cardProviderImage: Int,
    paymentType:String,
    statues: String = "Connected"
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

                Image(
                    painter = painterResource(id = cardProviderImage),
                    contentDescription =null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                )
            Spacer(modifier = Modifier.width(8.dp))

                Text(text = paymentType)
            Spacer(modifier = Modifier.weight(1f))


            Text(
                    text = statues,
                    color = MaterialTheme.colorScheme.primary
                )



        }

    }

}


