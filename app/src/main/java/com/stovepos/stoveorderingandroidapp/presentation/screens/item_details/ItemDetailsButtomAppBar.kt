package com.stovepos.stoveorderingandroidapp.presentation.screens.item_details

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stovepos.stoveorderingandroidapp.data.mongo_db.CartItem
import com.stovepos.stoveorderingandroidapp.data.mongo_db.OptionRealm
import com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.Option
import com.stovepos.stoveorderingandroidapp.utils.Constants
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.launch




@Composable
fun ItemDetailsBottomAppBar(
    itemPrice: Double?,
    itemQty: Int,
    increaseQty: () -> Unit,
    decreaseQty: () -> Unit,
    itemName: String? = "",
    itemId: Int?,
    menuCat: Int?,
    itemMods: List<Option>,
    itemDetailsViewModel: ItemDetailsViewModel = hiltViewModel(),
    navigateToCartScreen: () -> Unit,
    snackbarHostState: SnackbarHostState


) {
    val scope = rememberCoroutineScope()
    val app = App.create(Constants.APP_ID)
    val user = app.currentUser

    val addedItemMods = itemMods.map { itemMod->  OptionRealm().apply {
        this.modName = itemMod.name ?: ""
        this.modPrice = itemMod.price ?: 0.0
        this.modId = itemMod.id.toString()

    }

    }


    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier
    ){


            QtyButtons(
                increaseQty = { increaseQty() },
                decreaseQty = { decreaseQty() },
                itemQty = itemQty
            )
        Spacer(modifier =   Modifier.weight(0.5f))

        Button(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(end = 16.dp),
            onClick =  {
                scope.launch {
                    itemDetailsViewModel.addToCart(CartItem().apply {
                        this.itemName = itemName ?: ""
                        this.itemPrice = itemPrice.toString()
                        this.itemId = itemId ?: 1
                        this.itemMods = addedItemMods.toRealmList()
                        this.owner_id = user?.id ?: ""
                        this.itemQty = itemQty
                        this.itemImages_json = realmListOf()
                        this.menuCatId = menuCat ?: 1
                    },
                        onError = {},
                        onSuccess = {
                           scope.launch {
                               val result =    snackbarHostState.showSnackbar(
                                    message = "$itemName Added",
                                    actionLabel = "Go to Cart"
                                )
                               if(result == SnackbarResult.ActionPerformed){
                                   navigateToCartScreen.invoke()

                               }
                            }

                        }

                    )
                }




            }
        ) {

            Icon(
                imageVector = Icons.Outlined.AddShoppingCart,
                contentDescription = null,
                modifier = Modifier
            )
            Spacer(modifier =   Modifier.width(6.dp))
            Text(text = "\$ $itemPrice",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimary,
            )

        }

    }
}