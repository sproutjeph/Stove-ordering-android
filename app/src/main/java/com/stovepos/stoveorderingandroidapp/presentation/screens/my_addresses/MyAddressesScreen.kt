package com.stovepos.stoveorderingandroidapp.presentation.screens.my_addresses

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.stovepos.stoveorderingandroidapp.R
import com.stovepos.stoveorderingandroidapp.presentation.components.StoveTopAppBar

@Composable
fun MyAddressesScreen(
    onBackButtonClicked: () -> Unit = {},
) {


    Scaffold(
        topBar = {
            StoveTopAppBar(
                title =  R.string.my_addresses_screen_title,
                onBackButtonClicked = onBackButtonClicked,
                isMainScreen = false
            )
        },
    ) { paddingValues ->
        AddressDataField(paddingValues = paddingValues,)



    }

}

@Composable
fun   AddressDataField(
    paddingValues: PaddingValues,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        AddressDataField(
            addressHeader = "Home"
        )

        Spacer(modifier = Modifier.height(16.dp))
        AddressDataField(
            addressHeader = "My Office"
        )

        Spacer(modifier = Modifier.height(16.dp))
        AddressDataField(
            addressHeader = "My Apartment",
        )

        Spacer(modifier = Modifier.height(16.dp))
        AddressDataField(
            addressHeader = "Parent's House",
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
            Text(text = "Add New Address")
        }
    }



}


@Composable
fun AddressDataField(
    addressHeader:String,
    addressValue:String = "Times Square NYC, manhattan"
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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(
                modifier = Modifier
                    .size(40.dp),
                shape = RoundedCornerShape(50),
                color = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary

            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                )

            }
            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = addressHeader
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = addressValue,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize
                )

            }
            Spacer(modifier = Modifier.width(4.dp))


            Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    modifier = Modifier
                )

        }

    }

}