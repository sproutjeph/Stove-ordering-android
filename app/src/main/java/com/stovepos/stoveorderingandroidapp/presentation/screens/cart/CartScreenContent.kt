package com.stovepos.stoveorderingandroidapp.presentation.screens.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.stovepos.stoveorderingandroidapp.data.mongo_db.AllCartItems
import com.stovepos.stoveorderingandroidapp.data.mongo_db.RequestState
import org.mongodb.kbson.ObjectId

//@Preview(showBackground = true)
@Composable
fun CartScreenContent(
    totalPrice: Double = 12.00,
    paddingValues: PaddingValues = PaddingValues(),
    allCartItems: AllCartItems,
    navigateToCartItemDetails: (String) -> Unit = {},
    onDeleteItem: (id: ObjectId) -> Unit = {},
    totalItemsInCart: Int,

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
                text = "CART  ($totalItemsInCart)",
                modifier = Modifier
                    .padding(vertical = 16.dp)

            )
        }

        when(allCartItems){
            is RequestState.Success->{
                items(allCartItems.data){cartItem->
                    CartItem(
                        itemName = cartItem.itemName,
                        itemPrice = cartItem.itemPrice.toDouble(),
                        itemMods = cartItem.itemMods.toList(),
                        itemQuantity = cartItem.itemQty,
                        onClicked = {navigateToCartItemDetails(cartItem._id.toString())},
                        onDeleteItem = {onDeleteItem(cartItem._id)}

                    )

                }

            }
            else -> {
                item {
                    CircularProgressIndicator()

                }

            }
        }








    }

}