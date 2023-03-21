package com.stovepos.stoveorderingandroidapp.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stovepos.stoveorderingandroidapp.R
import com.stovepos.stoveorderingandroidapp.presentation.components.*


//@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    drawerState: DrawerState,
    onSignOutClicked: () -> Unit,
    onDeleteAllClicked: () -> Unit = {},
    onProfileClicked: () -> Unit,
    navigateToItemDetailsWithArgs: (String) -> Unit ={}

) {

    NavigationDrawer(
        drawerState = drawerState,
        onSignOutClicked = onSignOutClicked,
        onDeleteAllClicked = onDeleteAllClicked
    ) {

        Scaffold(
            topBar = {
                HomeScreenTopBar(
                    onProfileClicked = onProfileClicked
                )
            },
            bottomBar = {
                StoveBottomAppBar()
            }
        ) {contentPadding->


            Column(
                modifier = Modifier
                    .padding(contentPadding)
            ){

                FlashCard(
                    restaurantName = "Restaurant"

                )
                Divider()
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                ){

                    item { MenuCategoryButton() }

                    item{ MenuCategoryButton(text ="Burger") }

                }
                Divider()

                LazyColumn(){

//                    item{
//                        if(menuState.isLoading){
//                            CircularProgressIndicator(
//                                modifier = Modifier
//
//                            )
//                        }
//                    }


                    item{
                        MenuItem(
                            itemName = "Vegetarian Noodles",
                            itemPrice = "12.00",
                            itemDesc = "Vegetarian noodles are a tasty and satisfying meal"
                        ){
                        }

                    }

                }
            }

        }
    }



}



@Composable
fun NavigationDrawer(
    drawerState: DrawerState,
    onSignOutClicked: () -> Unit,
    onDeleteAllClicked: () -> Unit,
    content: @Composable () -> Unit
) {
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
                                        Image(
                                            painter = painterResource(id = R.drawable.photo_placeholder),
                                            contentDescription = "Profile Image",
                                            modifier = Modifier
                                                .size(80.dp)
                                                .clip(shape = RoundedCornerShape(50))
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


@Composable
fun CustomNavItem() {
    
}