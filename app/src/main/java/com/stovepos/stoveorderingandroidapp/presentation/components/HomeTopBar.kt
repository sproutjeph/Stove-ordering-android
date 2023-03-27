package com.stovepos.stoveorderingandroidapp.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stovepos.stoveorderingandroidapp.utils.Constants.APP_ID
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.ext.profileAsBsonDocument


@Preview(showBackground = true)
@Composable
fun HomeScreenTopBar(
    onProfileClicked: ()-> Unit = {}
) {
          val userData = App.create(APP_ID).currentUser?.profileAsBsonDocument()


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),


        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {

        Row (
            modifier = Modifier
                .padding(8.dp),

            ){

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(userData?.getString("picture")?.value)
                    .build(),
                contentDescription = "User Image",
                modifier = Modifier
                    .size(45.dp)
                    .clip(shape = RoundedCornerShape(50))
                    .clickable { onProfileClicked.invoke() },
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .padding(top = 4.dp)
            ) {
                Text(
                    text = "Hello",
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,

                )
                userData?.getString("name")?.value?.let {
                    Text(
                        text = it,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,

                        )
                }

            }


        }


        Row(
            modifier = Modifier
                .padding(8.dp),

            ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(shape = RoundedCornerShape(50))
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = RoundedCornerShape(50)
                    )
                    .clickable {

                    },

                ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(24.dp)
                )
            }



            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(shape = RoundedCornerShape(50))
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = RoundedCornerShape(50)
                    )
                    .clickable {

                    },

                ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(24.dp)
                )


            }
        }


    }

}