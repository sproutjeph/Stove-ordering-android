package com.stovepos.stoveorderingandroidapp.presentation.screens.cart

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stovepos.stoveorderingandroidapp.R


@Preview(showBackground = true)
@Composable
fun CartItem(
    itemImage: Int = R.drawable.restaurant_1,
    itemName: String = "Breakfast Grill (SEATING)",
    itemQuantity: String = "3 items | and Mods",
    itemPrice: Double = 2.00,
    itemMods: List<String> = listOf("salad $12", "ice $1.2"),
    onClicked: () -> Unit = {}


) {
    var modsContainerOpened by remember { mutableStateOf(false) }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding( vertical = 6.dp)
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
                    painter = painterResource(id = itemImage),
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
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = itemName,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = itemQuantity,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$$itemPrice",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    TextButton(
                        onClick = { /*TODO*/ },

                    ) {
                        Text(
                            text = "Remove",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }


                if(itemMods.isNotEmpty()){
                    ShowItemModifierButton(
                        modsContainerOpened = modsContainerOpened
                    ) {
                        modsContainerOpened = !modsContainerOpened

                    }
                }

                AnimatedVisibility(
                    visible = modsContainerOpened,
                    enter = fadeIn() + expandVertically(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioHighBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
                ) {
                    Column(
                        modifier = Modifier
                    ) {
                        CartItemModifier()
                        CartItemModifier()
                        CartItemModifier()




                    }

                }
            }

        }




    }


}


@Composable
fun ShowItemModifierButton(
    modsContainerOpened: Boolean = true,
    onClick: () -> Unit = {}
) {
    TextButton(
        onClick = onClick,
    ) {
        Text(
            text = if (!modsContainerOpened) "Show Item Modifiers" else "Hide Item Modifiers",
            style = TextStyle(fontSize = MaterialTheme.typography.bodySmall.fontSize)
        )
    }
}


@Composable
fun CartItemModifier() {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Salad")
            Text(text = "+ $1.2")

            TextButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            }


        }

    }
}
