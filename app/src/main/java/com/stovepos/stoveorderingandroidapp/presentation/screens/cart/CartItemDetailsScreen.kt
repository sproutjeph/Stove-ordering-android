package com.stovepos.stoveorderingandroidapp.presentation.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stovepos.stoveorderingandroidapp.R
import com.stovepos.stoveorderingandroidapp.data.mongo_db.OptionRealm
import io.realm.kotlin.types.EmbeddedRealmObject

@Composable
fun CartItemDetailsScreen(
    onBackPressed: () -> Unit,
    cartScreenViewModel: CartScreenViewModel = viewModel()

) {
    val selectedCartItem = cartScreenViewModel.selectedCartItem.value


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ){
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
                        .padding(top = 35.dp, start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier
                            .size(40.dp),
                        shape = RoundedCornerShape(50)

                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    onBackPressed()
                                }
                        )

                    }
                    Surface(
                        modifier = Modifier
                            .size(40.dp),
                        shape = RoundedCornerShape(50)

                    ) {

                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    onBackPressed()
                                }
                        )

                    }
                }


            }

            if (selectedCartItem != null) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)


                ) {
                    Text(
                        text = selectedCartItem.itemName,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                        color = MaterialTheme.colorScheme.onSurface
                    )


                    Text(
                        text =  selectedCartItem.itemMods.joinToString(", ") { it.modName },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface

                    )

                }

                LazyColumn(
                    modifier = Modifier
                        .padding(16.dp)
                ){

                    item {
                        Text(
                            text = "Added Item Modifiers",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                    items(selectedCartItem.itemMods){itemMod->
                        OutlinedButton(
                            onClick = {  },
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                                contentColor = MaterialTheme.colorScheme.onSurface,
                            )


                        ) {
                            itemMod.modName?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier
                                        .padding(end = 16.dp)
                                )
                            }
                            Text(
                                text = "+ \$${itemMod.modPrice}",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                            )
                            Spacer(modifier = Modifier.weight(1f))

                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.error
                            )
                        }

                    }
                }

            }


        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 40.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(4.dp),
            elevation = ButtonDefaults.buttonElevation(4.dp)
        ) {
            Text(text = "Update Cart Item")

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "$${selectedCartItem?.itemPrice.toString()}"
            )

        }
    }



}


