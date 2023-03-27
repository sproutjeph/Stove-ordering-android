package com.stovepos.stoveorderingandroidapp.presentation.screens.item_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodapp.features.item_details.components.*
import com.stovepos.stoveorderingandroidapp.R
import com.stovepos.stoveorderingandroidapp.presentation.screens.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailsScreen(
    itemId: String,
    onBackPressed: ()-> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()

) {

    val menuDataState = homeViewModel.menuDataState.value

    val selectedItem = menuDataState.menuData?.menuItems?.find { it.itemid == itemId.toInt() }

    var itemQty by remember { mutableStateOf(1) }

    var itemPrice by remember { mutableStateOf(selectedItem?.item_price?.toDouble()) }

    itemPrice = selectedItem?.item_price?.toDouble()?.times(itemQty)

    fun increaseQty() {
        itemQty++
    }

    fun decreaseQty() {
        if (itemQty > 1) {
            itemQty--
        }
    }





    Scaffold(
        topBar = {},
        bottomBar = {}
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .background(color = MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Box(
                modifier = Modifier

            ) {
                Image(
                    painter = painterResource(id = R.drawable.item_image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable {
                                onBackPressed()
                            },
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        tint = MaterialTheme.colorScheme.primary

                    )

                }
            }

            if (selectedItem == null) {
                CircularProgressIndicator(modifier = Modifier.padding(vertical = 16.dp))
            } else {
                Text(
                    text = selectedItem.item_name,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(vertical = 4.dp)

                )

                Text(
                    text = selectedItem.item_name,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .width(200.dp)


                )
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                LazyColumn(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.surface)
                        .padding(horizontal = 16.dp)
                ) {

                    item {
                        ItemModifierTitle(
                            title = selectedItem.itemOptions_json[0].name
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(4.dp))

                    }


                    items(selectedItem.itemOptions_json[0].options) { itemOption ->
                        ItemModifier(
                            modItem = itemOption
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(4.dp))

                    }

                    if (selectedItem.itemOptions_json.size > 1 && selectedItem.itemOptions_json[1].options.isNotEmpty()) {
                        item {
                            ItemModifierTitle(
                                title = selectedItem.itemOptions_json[1].name
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(4.dp))

                        }
                        items(selectedItem.itemOptions_json[1].options) { itemOption ->
                            ItemModifier(
                                modItem = itemOption
                            )
                        }


                    }

                    if (selectedItem.itemOptions_json.size > 2) {
                        item {
                            ItemModifierTitle(
                                title = selectedItem.itemOptions_json[2].name
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(4.dp))

                        }
                        items(selectedItem.itemOptions_json[2].options) { itemOption ->
                            ItemModifier(
                                modItem = itemOption

                            )
                        }

                    }




                    item {
                        SpecialRequest()

                    }

                    item {
                        QtyButtons(
                            itemQty = itemQty,
                            increaseQty = { increaseQty() },
                            decreaseQty = { decreaseQty() }
                        )
                    }


                    item {
                        AddToCartButton(
                            itemPrice = itemPrice ?: selectedItem.item_price.toDouble(),
                        )
                    }
                }


            }

        }
    }


}






