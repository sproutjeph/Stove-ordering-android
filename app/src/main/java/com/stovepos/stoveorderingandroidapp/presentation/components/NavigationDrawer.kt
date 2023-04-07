package com.stovepos.stoveorderingandroidapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stovepos.stoveorderingandroidapp.utils.Constants
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.ext.profileAsBsonDocument

@Composable
fun NavigationDrawer(
    drawerState: DrawerState,
    onSignOutClicked: () -> Unit,
    content: @Composable () -> Unit
) {
    val userData = App.create(Constants.APP_ID).currentUser?.profileAsBsonDocument()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                content = {
                    LazyColumn(){
                        item {
                            Row(modifier = Modifier
                                .padding( 12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(userData?.getString("picture")?.value)
                                        .build(),
                                    contentDescription = "User Image",
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(shape = RoundedCornerShape(50)),
                                    contentScale = ContentScale.Crop,
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Jephthah Mbah",
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                                )
                            }

                        }

                        item {
                            Divider(modifier = Modifier
                                .fillMaxWidth()
                            )
                        }

                        item {
                            NavigationDrawerItem(
                                label = {
                                    Row(modifier = Modifier.padding(horizontal = 12.dp)) {
                                        Icon(
                                            imageVector = Icons.Outlined.AddShoppingCart,
                                            contentDescription = "Shopping Cart Icon",
                                            tint = MaterialTheme.colorScheme.onSurface
                                        )
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Text(
                                            text = "My Cart",
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                },
                                selected = false,
                                onClick = {}
                            )
                        }


                        item {
                            NavigationDrawerItem(
                                label = {
                                    Row(modifier = Modifier.padding(horizontal = 12.dp)) {
                                        Icon(
                                            imageVector = Icons.Outlined.AccountBox,
                                            contentDescription = "Account Icon",
                                            tint = MaterialTheme.colorScheme.onSurface
                                        )
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Text(
                                            text = "Account",
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                },
                                selected = false,
                                onClick = {}
                            )
                        }


                        item {
                            NavigationDrawerItem(
                                label = {
                                    Row(modifier = Modifier.padding(horizontal = 12.dp)) {
                                        Icon(
                                            imageVector = Icons.Outlined.Logout,
                                            contentDescription = "Logout Icon",
                                            tint = MaterialTheme.colorScheme.onSurface
                                        )
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Text(
                                            text = "Sign Out",
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                },
                                selected = false,
                                onClick = onSignOutClicked
                            )
                        }


                    }

                }
            )
        },
        content = content
    )
}