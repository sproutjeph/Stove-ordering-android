package com.stovepos.stoveorderingandroidapp.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.stovepos.stoveorderingandroidapp.data.local.MenuCatButtonModel
import com.stovepos.stoveorderingandroidapp.data.local.MenuItemModel
import com.stovepos.stoveorderingandroidapp.presentation.components.*

const val ALL_MENU_CAT_ID = 1111

data class MenuCategory(
    val id: Int,
    val categoryName: String,
    val items: List<MenuItemModel>
)


//@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    drawerState: DrawerState,
    onSignOutClicked: () -> Unit,
    onDeleteAllClicked: () -> Unit = {},
    onProfileClicked: () -> Unit,
    navigateToItemDetails: (String) -> Unit,
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()

) {

    val menuDataState = homeViewModel.menuDataState.value
    val venueInfo = homeViewModel.venueInfo.collectAsState().value

    val menuItems = homeViewModel.menuItems.collectAsState().value
    val menCatButtons = homeViewModel.menuCatButtons.collectAsState().value



    var selectedMenuCategory by remember {
        mutableStateOf(ALL_MENU_CAT_ID)
    }






    fun menuCategoryData(): List<MenuCategory> {

        return menCatButtons.map { category ->
            MenuCategory(
                id = category.menuCat,
                categoryName = category.menuName,
                items = menuItems.filter { it.menuCatId == category.menuCat }
            )
        }
    }

    Log.d("MENUS", "HomeScreen: ${menuCategoryData()}")



    fun onMenuCategoryButtonClicked(categoryId: Int){
        selectedMenuCategory = categoryId
    }

    val categoryButtons = menCatButtons.fold(
        mutableListOf(MenuCatButtonModel(menuName = "All Items", menuCat = ALL_MENU_CAT_ID,  sortOrder = 0, ))
    ) { acc, curr -> acc.apply { add(curr) } }

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
                StoveBottomAppBar(navController = navController)
            }
        ) {contentPadding->

                LazyColumn(
                    modifier = Modifier
                        .padding(contentPadding)
                ){

                    item{
                        FlashCard(restaurantName = if (venueInfo.isNotEmpty()) venueInfo[0].venueName else "Restaurant Name",)
                    }

                    if(menuDataState.isLoading){
                        item{
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ){
                                CircularProgressIndicator()
                            }

                        }
                    }else{
                        stickyHeader{
                            MenuCategoryButtonRow(
                                menuCategories = categoryButtons,
                                selectedMenuCategory = selectedMenuCategory,
                                onMenuCategoryButtonClicked = { onMenuCategoryButtonClicked(it) }
                            )

                        }

                            items(menuCategoryData()){ menuCategory->

                                if(
                                    menuCategory.id == selectedMenuCategory ||
                                    selectedMenuCategory == ALL_MENU_CAT_ID
                                )
                                    menuCategory.items.forEach { menuItem ->
                                        MenuItem(
                                            itemName = menuItem.itemName,
                                            itemPrice = menuItem.itemPrice,
                                            itemDesc = menuItem.itemName,
                                            onMenuItemClicked = {
                                                navigateToItemDetails(menuItem.itemId.toString())
                                            }
                                        )

                                    }

                                }


                        }

                        }

                    }


                }

        }











@Composable
fun MenuCategoryButtonRow(
    menuCategories: List<MenuCatButtonModel> = emptyList(),
    selectedMenuCategory: Int = ALL_MENU_CAT_ID,
    onMenuCategoryButtonClicked: (Int) -> Unit

) {
    Surface(
        modifier = Modifier,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        tonalElevation = 1.dp


    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ){

            items(menuCategories){menuCategory->
                MenuCategoryButton(
                    text = menuCategory.menuName,
                    backgroundColor = if(menuCategory.menuCat == selectedMenuCategory){
                        MaterialTheme.colorScheme.primary
                    } else MaterialTheme.colorScheme.surface,
                    textColor = if(menuCategory.menuCat == selectedMenuCategory){
                        MaterialTheme.colorScheme.onPrimary
                    } else MaterialTheme.colorScheme.onSurface,
                    onClick = { onMenuCategoryButtonClicked(menuCategory.menuCat) }
                )

            }

        }
    }



}


