package com.stovepos.stoveorderingandroidapp.presentation.screens.restaurents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.DeliveryDining
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stovepos.stoveorderingandroidapp.R
import com.stovepos.stoveorderingandroidapp.ui.theme.RomanticColor

@Composable
fun RestaurantScreenContent(
    paddingValues: PaddingValues,
    onRestaurantClick: () -> Unit = {}
) {

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
    ){
        items(listOfRestaurants){restaurantData->
            Restaurant(
                restaurantImage = restaurantData.restaurantImage,
                restaurantName = restaurantData.restaurantName,
                restaurantDistance = restaurantData.restaurantDistance,
                restaurantDeliveryFee = restaurantData.restaurantDeliveryFee,
                onClicked = onRestaurantClick
            )

        }
    }

}








@Preview(showBackground = true)
@Composable
fun Restaurant(
    restaurantImage: Int = R.drawable.restaurant_1,
    restaurantName: String = "Breakfast Grill (SEATING)",
    restaurantDistance: String = "1.4 Km | 20-30 min",
    restaurantDeliveryFee: Double = 2.00,
    onClicked: () -> Unit = {}

) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .background(color = MaterialTheme.colorScheme.surface)
            .clickable {
                onClicked()
            },
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Surface(
                modifier = Modifier
                    .size(130.dp)
                    .weight(1f),
                shape = RoundedCornerShape(12.dp),
            ) {
                Image(
                    painter = painterResource(id = restaurantImage),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp))
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = restaurantName,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = restaurantDistance,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row{
                    Icon(imageVector = Icons.Outlined.DeliveryDining, contentDescription = null)

                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "$$restaurantDeliveryFee",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                        tint = RomanticColor
                    )

                }
            }

        }
    }


}

data class RestaurantData(
    val restaurantImage: Int,
    val restaurantName: String,
    val restaurantDistance: String,
    val restaurantDeliveryFee: Double,
    val venueId: Int = 1,
)

val listOfRestaurants = listOf<RestaurantData>(
    RestaurantData(
        restaurantImage = R.drawable.restaurant_1,
        restaurantName = "Breakfast Grill (SEATING)",
        restaurantDistance = "1.4 Km | 20-30 min",
        restaurantDeliveryFee = 2.00,
        venueId = 1,
    ),
    RestaurantData(
        restaurantImage = R.drawable.restaurant_2,
        restaurantName = "Quick Service Grill",
        restaurantDistance = "1.4 Km | 20-30 min",
        restaurantDeliveryFee = 2.00,
        venueId = 2,
    ),
    RestaurantData(
        restaurantImage = R.drawable.restaurant_3,
        restaurantName = "Sunny Side Kitchen",
        restaurantDistance = "1.4 Km | 20-30 min",
        restaurantDeliveryFee = 2.00,
        venueId = 3
    ),
    RestaurantData(
        restaurantImage = R.drawable.restaurant_4,
        restaurantName = "Paradiso",
        restaurantDistance = "1.4 Km | 20-30 min",
        restaurantDeliveryFee = 2.00,
        venueId = 4
    ),
    RestaurantData(
        restaurantImage = R.drawable.restaurant_5,
        restaurantName = "Sopranos Pizza",
        restaurantDistance = "1.4 Km | 20-30 min",
        restaurantDeliveryFee = 2.00,
        venueId = 5
    ),
    RestaurantData(
        restaurantImage = R.drawable.restaurant_6,
        restaurantName = "Signature Bagel and Deli",
        restaurantDistance = "1.4 Km | 20-30 min",
        restaurantDeliveryFee = 2.00,
        venueId = 6
    ),
    RestaurantData(
        restaurantImage = R.drawable.restaurant_7,
        restaurantName = "Chicken Plus",
        restaurantDistance = "1.4 Km | 20-30 min",
        restaurantDeliveryFee = 1.00,
        venueId = 7
    ),
    RestaurantData(
        restaurantImage = R.drawable.restaurant_8,
        restaurantName = "Tamales Escondido",
        restaurantDistance = "1.4 Km | 20-30 min",
        restaurantDeliveryFee = 1.50,
        venueId = 8
    ),
    RestaurantData(
        restaurantImage = R.drawable.item_image,
        restaurantName = "La Tapatia",
        restaurantDistance = "1.4 Km | 10-20 min",
        restaurantDeliveryFee = 2.00,
        venueId = 9
    ),
    RestaurantData(
        restaurantImage = R.drawable.restaurant_8,
        restaurantName = "TBandi Lou Steakhouse",
        restaurantDistance = "1.4 Km | 10-20 min",
        restaurantDeliveryFee = 2.00,
        venueId = 10
    ),
    RestaurantData(
        restaurantImage = R.drawable.item_image,
        restaurantName = "Beckys Juice Bar",
        restaurantDistance = "1.4 Km | 10-20 min",
        restaurantDeliveryFee = 2.00,
        venueId = 11
    ),
    RestaurantData(
        restaurantImage = R.drawable.restaurant_4,
        restaurantName = "Mikes Backyard",
        restaurantDistance = "1.4 Km | 10-20 min",
        restaurantDeliveryFee = 2.00,
        venueId = 12
    ),
    RestaurantData(
        restaurantImage = R.drawable.item_image,
        restaurantName = "Breakfast Grill (SEATING)",
        restaurantDistance = "1.4 Km | 10-20 min",
        restaurantDeliveryFee = 2.00,
        venueId = 13
    ),
    RestaurantData(
        restaurantImage = R.drawable.restaurant_4,
        restaurantName = "QSR Yummy",
        restaurantDistance = "1.4 Km | 10-20 min",
        restaurantDeliveryFee = 2.00,
        venueId = 16
    ),
    RestaurantData(
        restaurantImage = R.drawable.item_image,
        restaurantName = "DFA Deals",
        restaurantDistance = "1.4 Km | 10-20 min",
        restaurantDeliveryFee = 2.00,
        venueId = 22
    ),
    RestaurantData(
        restaurantImage = R.drawable.restaurant_4,
        restaurantName = "Publick House",
        restaurantDistance = "1.4 Km | 10-20 min",
        restaurantDeliveryFee = 2.00,
        venueId = 23
    ),
    RestaurantData(
        restaurantImage = R.drawable.restaurant_2,
        restaurantName = "S&D Oyster House",
        restaurantDistance = "1.4 Km | 10-20 min",
        restaurantDeliveryFee = 2.00,
        venueId = 24
    ),
    RestaurantData(
        restaurantImage = R.drawable.restaurant_1,
        restaurantName = "Shaks Bistro",
        restaurantDistance = "1.4 Km | 10-20 min",
        restaurantDeliveryFee = 2.00,
        venueId = 25
    ),

)