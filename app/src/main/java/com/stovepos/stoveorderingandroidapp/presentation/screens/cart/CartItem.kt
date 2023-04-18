package com.stovepos.stoveorderingandroidapp.presentation.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stovepos.stoveorderingandroidapp.R
import com.stovepos.stoveorderingandroidapp.data.mongo_db.OptionRealm



@Composable
fun CartItem(
    itemImage: Int = R.drawable.item_image,
    itemName: String = "Breakfast Grill (SEATING)",
    itemQuantity: Int = 3,
    itemPrice: Double = 2.00,
    itemMods: List<OptionRealm> = listOf(),
    onClicked: () -> Unit = {},
    onDeleteItem: () -> Unit = {},
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = MaterialTheme.colorScheme.surface)
            .clickable {
                onClicked()
            },

    ) {
        Box(
            modifier = Modifier
                .padding( end = 10.dp)

        ){
            Image(  painter = painterResource(id = itemImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Surface(
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.CenterStart),
                shape = RoundedCornerShape(50),
                tonalElevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = itemQuantity.toString(),
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier,
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,

                    )
                    Spacer(modifier = Modifier.width(2.dp))

                    Text(
                        text = "X",
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier,
                        fontWeight = FontWeight.Bold,
                        fontSize = 9.sp
                    )
                }

            }

        }

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(text = itemName)

            Text(
                text = itemMods.joinToString(", ") { it.modName },
                style = MaterialTheme.typography.bodySmall,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "$$itemPrice",
            )

        }

        Surface(
            modifier = Modifier
                .size(35.dp)
                .align(Alignment.CenterVertically)
                .clickable(
                    onClick = {
                        onDeleteItem()

                    }
                ),
            shape = RoundedCornerShape(50),
            tonalElevation = 4.dp
        ) {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .padding(8.dp)
            )
        }


    }



}























