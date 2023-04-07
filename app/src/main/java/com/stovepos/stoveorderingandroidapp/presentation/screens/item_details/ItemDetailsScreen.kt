package com.stovepos.stoveorderingandroidapp.presentation.screens.item_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.AddShoppingCart
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
import com.stovepos.stoveorderingandroidapp.R
import com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.Option

@Composable
fun ItemDetailsScreen(
    onBackPressed: () -> Unit,
    navigateToCartScreen: () -> Unit,
    itemDetailsViewModel: ItemDetailsViewModel = hiltViewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }

    val selectedItem = itemDetailsViewModel.menuItem.value
    var itemPrice by remember { mutableStateOf(selectedItem?.itemPrice?.toDouble()) }
    var itemQty by remember { mutableStateOf(1) }
    var selectedItemMods by remember { mutableStateOf(listOf<Option>()) }
    var totalItemModsPrice by remember { mutableStateOf(selectedItemMods.sumOf { it.price ?: 0.0 }) }

    fun increaseQty() {
        itemQty++

    }

    fun decreaseQty() {
        if (itemQty > 1) {
            itemQty--
        }
    }


    fun handleItemModSelection(itemMod: Option) {

        val exists = selectedItemMods.find { it.name == itemMod.name }


        if (exists == null) {
            selectedItemMods = selectedItemMods + itemMod
            totalItemModsPrice += (itemMod.price ?: 0.0)

        } else {

            selectedItemMods = selectedItemMods - itemMod
            totalItemModsPrice -= (itemMod.price ?: 0.0)
        }

        itemPrice = selectedItem?.itemPrice?.toDouble()?.plus(totalItemModsPrice)

    }




    Scaffold(
        topBar = {},
        bottomBar = {
            ItemDetailsBottomAppBar(
                itemPrice = itemPrice?.times(itemQty) ?:
                selectedItem?.itemPrice?.toDouble()?.times(itemQty),
                itemQty = itemQty,
                increaseQty = { increaseQty() },
                decreaseQty = { decreaseQty() },
                itemName = selectedItem?.itemName,
                itemId = selectedItem?.itemId,
                menuCat = selectedItem?.menuCatId,
                itemMods = selectedItemMods,
                navigateToCartScreen = navigateToCartScreen,
                snackbarHostState = snackbarHostState

            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },

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
                        imageVector = Icons.Outlined.AddShoppingCart,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable { navigateToCartScreen.invoke() },
                        tint = MaterialTheme.colorScheme.primary

                    )

                }
            }

            if (selectedItem == null) {
                CircularProgressIndicator(modifier = Modifier.padding(vertical = 16.dp))
            } else {
                Text(
                    text = selectedItem.itemName,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(vertical = 4.dp)

                )

                Text(
                    text = selectedItem.itemName,
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

                    if (selectedItem.itemOptionsJson.isEmpty()) {
                        item {
                            ItemModifierTitle(
                                title = "No Options",
                            )
                        }

                    } else {
                        item {
                            ItemModifierTitle(
                                title = selectedItem.itemOptionsJson[0].name
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(4.dp))

                        }
                        items(selectedItem.itemOptionsJson[0].options) { itemOption ->
                            ItemModifier(
                                modItem = itemOption,
                            ) {
                                handleItemModSelection(it)
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(4.dp))

                    }

                    if (selectedItem.itemOptionsJson.size > 1 && selectedItem.itemOptionsJson[1].options.isNotEmpty()) {
                        item {
                            ItemModifierTitle(
                                title = selectedItem.itemOptionsJson[1].name
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(4.dp))

                        }
                        items(selectedItem.itemOptionsJson[1].options) { itemOption ->
                            ItemModifier(
                                modItem = itemOption
                            ) {
                                handleItemModSelection(it)
                            }
                        }


                    }

                    if (selectedItem.itemOptionsJson.size > 2 && selectedItem.itemOptionsJson[2].options.isNotEmpty()) {
                        item {
                            ItemModifierTitle(
                                title = selectedItem.itemOptionsJson[2].name
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(4.dp))

                        }
                        items(selectedItem.itemOptionsJson[2].options) { itemOption ->
                            ItemModifier(
                                modItem = itemOption

                            ) {
                                handleItemModSelection(it)
                            }
                        }

                    }

                    if (selectedItem.itemOptionsJson.size > 3 && selectedItem.itemOptionsJson[3].options.isNotEmpty()) {
                        item {
                            ItemModifierTitle(
                                title = selectedItem.itemOptionsJson[3].name
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(4.dp))

                        }
                        items(selectedItem.itemOptionsJson[3].options) { itemOption ->
                            ItemModifier(
                                modItem = itemOption

                            ) {
                                handleItemModSelection(it)
                            }
                        }

                    }

                    item {
                        SpecialRequest()

                    }

                }


            }

        }
    }


}






