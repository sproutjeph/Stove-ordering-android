package com.stovepos.stoveorderingandroidapp.presentation.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stovepos.stoveorderingandroidapp.R
import com.stovepos.stoveorderingandroidapp.presentation.components.StoveTopAppBar
import com.stovepos.stoveorderingandroidapp.utils.Constants
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.ext.profileAsBsonDocument
import org.mongodb.kbson.BsonDocument

@Composable
fun ProfileScreen(
    onBackButtonClicked: () -> Unit = {},
    navigateToAddressScreen: () -> Unit,
    navigateToMyPaymentsCardsScreen: () -> Unit,
    ) {
    val userData = App.create(Constants.APP_ID).currentUser?.profileAsBsonDocument()


    Scaffold(
        topBar = {
                 StoveTopAppBar(
                     title =  R.string.profile_screen_title,
                     onBackButtonClicked = onBackButtonClicked,
                     isMainScreen = false
                 )
        },
    ) { paddingValues ->
        ProfileScreenContent(
            paddingValues = paddingValues,
            userData = userData,
            navigateToAddressScreen = navigateToAddressScreen,
            navigateToMyPaymentsCardsScreen = navigateToMyPaymentsCardsScreen
        )



    }

}

@Composable
fun ProfileScreenContent(
    paddingValues: PaddingValues,
    userData: BsonDocument? = null,
    navigateToAddressScreen: () -> Unit,
    navigateToMyPaymentsCardsScreen: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(userData?.getString("picture")?.value)
                    .build(),
                contentDescription = "User Image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(shape = RoundedCornerShape(50)),
                contentScale = ContentScale.Crop,
            )

            Surface(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(top = 8.dp)
                    .size(35.dp),
                shape = RoundedCornerShape(50),
                color = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                )

            }

        }

        Spacer(modifier = Modifier.height(16.dp))
        UserDataField(
            text = userData?.getString("name")?.value.toString().uppercase()
        )

        Spacer(modifier = Modifier.height(16.dp))
        UserDataField( text = "12/12/1999", icon = Icons.Default.DateRange)

        Spacer(modifier = Modifier.height(16.dp))
        UserDataField(
            text = userData?.getString("email")?.value.toString(),
            icon = Icons.Default.Email
        )

        Spacer(modifier = Modifier.height(16.dp))
        UserDataField(
            text = "+1 123 456 789",
            icon = Icons.Default.Phone
        )

        Spacer(modifier = Modifier.height(16.dp))
        UserDataField(
            text = "United States",
            icon = Icons.Default.LocationCity
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navigateToAddressScreen.invoke()},
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(16.dp),
        ) {
            Text(text = "My Addresses")
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navigateToMyPaymentsCardsScreen.invoke() },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(16.dp),
        ) {
            Text(text = "My Payment Methods")
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)

        }


        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(16.dp),
        ) {
            Text(text = "Update")
        }
    }


    
}


@Composable
fun UserDataField(
    icon:ImageVector? = null,
    text:String,
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
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = text)
            if (icon != null) {
                Icon(imageVector = icon, contentDescription = null)
            }

        }

    }

}