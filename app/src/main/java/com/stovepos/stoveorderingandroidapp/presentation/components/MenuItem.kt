package com.stovepos.stoveorderingandroidapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stovepos.stoveorderingandroidapp.R
import com.stovepos.stoveorderingandroidapp.ui.theme.HumorousColor

//@Preview(showBackground = true)
@Composable
fun MenuItem(
    imageId: Int = R.drawable.item_image,
    itemName: String = "Food Name",
    itemDesc: String = "",
    itemPrice: String = "Price",
    onAddToCartClicked: () -> Unit = {},
    onMenuItemClicked: () -> Unit = {}

) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal =  16.dp, vertical = 8.dp)
            .background(color = MaterialTheme.colorScheme.surface)
            .clickable {
                onMenuItemClicked()
            },
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Surface(
                modifier = Modifier
                    .size(150.dp)
                    .weight(1f),
                shape = RoundedCornerShape(12.dp),
            ) {
                Image(
                    painter = painterResource(id = imageId),
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
                Text(
                    text = itemName,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = itemDesc,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Price: $$itemPrice",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Row(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = HumorousColor

                    )
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = HumorousColor


                    )
                    Icon(imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = HumorousColor
                    )
                    Icon(imageVector = Icons.Filled.Star, contentDescription = null)

                }

            }

        }
    }


}