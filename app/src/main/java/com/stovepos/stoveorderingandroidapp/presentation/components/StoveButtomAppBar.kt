package com.stovepos.stoveorderingandroidapp.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.stovepos.stoveorderingandroidapp.navigation.Screen



@Composable
fun StoveBottomAppBar(
    navController: NavController,
    homeScreenState: MutableState<BottomNavType>
){
    val navItems = listOf("Home","Orders", "Venues", "Payment","Profile")


    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier
    ) {

        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surface,

            ) {
            navItems.forEach {navItem->
                NavigationBarItem(
                    selected = homeScreenState.value == when(navItem){
                        "Home" -> BottomNavType.Home
                        "Orders" -> BottomNavType.Orders
                        "Venues" -> BottomNavType.Venues
                        "Payment" -> BottomNavType.Payment
                        "Profile" -> BottomNavType.Profile
                        else -> BottomNavType.Home
                    },
                    onClick = {
//                        when(navItem){
//                            "Home" -> homeScreenState.value = BottomNavType.Home
//                            "Orders" -> homeScreenState.value = BottomNavType.Orders
//                            "Venues" -> homeScreenState.value = BottomNavType.Venues
//                            "Payment" -> homeScreenState.value = BottomNavType.Payment
//                            "Profile" -> homeScreenState.value = BottomNavType.Profile
//                        }
                        navController.navigate(route = when(navItem){
                            "Home" -> Screen.Home.route
                            "Orders" -> Screen.Home.route
                            "Venues" -> Screen.Restaurants.route
                            "Payment" -> Screen.MyPaymentCards.route
                            "Profile" -> Screen.Profile.route
                            else -> Screen.Home.route
                        })


                    },


                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onSurface,
                        selectedTextColor = MaterialTheme.colorScheme.onSurface,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurface

                    ),
                    icon = {
                        when(navItem) {
                            "Home"-> Icon(imageVector = Icons.Outlined.Home,
                                contentDescription = null,
                                modifier = Modifier,
                                tint = LocalContentColor.current
                            )
                            "Orders" -> Icon(imageVector = Icons.Outlined.PendingActions,
                                contentDescription = null,
                                modifier = Modifier,
                                tint = LocalContentColor.current

                            )
                            "Venues" -> Icon(imageVector = Icons.Outlined.Restaurant,
                                contentDescription = null,
                                modifier = Modifier,
                                tint = LocalContentColor.current

                            )
                            "Payment" -> Icon(imageVector = Icons.Outlined.CreditCard,
                                contentDescription = null,
                                modifier = Modifier,
                                tint = LocalContentColor.current

                            )
                            "Profile" -> Icon(imageVector = Icons.Outlined.AccountBox,
                                contentDescription = null,
                                modifier = Modifier,
                                tint = LocalContentColor.current

                            )
                        }
                    },
                    modifier = Modifier,
                    label = { Text(text =navItem) },

                )



            }

        }

    }
}