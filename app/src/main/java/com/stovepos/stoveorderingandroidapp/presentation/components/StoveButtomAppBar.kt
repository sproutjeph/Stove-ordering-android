package com.stovepos.stoveorderingandroidapp.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StoveBottomAppBar(
){
    val navItems = listOf("Home","Orders", "Cart", "Payment","Profile")


    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier
    ) {

        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surface,

            ) {
            navItems.forEach {navItem->
                NavigationBarItem(selected = false,
                    onClick = {
//                        navController.navigate(route = when(navItem){
//                            "Home" -> ServiceReportScreens.HomeScreen.name
//                            "Reports" -> ServiceReportScreens.ReportsScreen.name
//                            "Students" -> ServiceReportScreens.StudentsScreen.name
//                            "Interests" -> ServiceReportScreens.InterestedPersonsScreen.name
//                            "Schedule" -> ServiceReportScreens.ScheduleScreen.name
//                            else -> ServiceReportScreens.HomeScreen.name
//                        })


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
                                modifier = Modifier
                            )
                            "Orders" -> Icon(imageVector = Icons.Outlined.PendingActions,
                                contentDescription = null,
                                modifier = Modifier
                            )
                            "Cart" -> Icon(imageVector = Icons.Outlined.AddShoppingCart,
                                contentDescription = null,
                                modifier = Modifier
                            )
                            "Payment" -> Icon(imageVector = Icons.Outlined.CreditCard,
                                contentDescription = null,
                                modifier = Modifier
                            )
                            "Profile" -> Icon(imageVector = Icons.Outlined.AccountBox,
                                contentDescription = null,
                                modifier = Modifier
                            )
                        }
                    },
                    modifier = Modifier,
                    label = { Text(text =navItem) }
                )



            }

        }

    }
}