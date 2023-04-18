package com.stovepos.stoveorderingandroidapp.presentation.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
@OptIn( ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    drawerState: DrawerState,
    onSignOutClicked: () -> Unit,
    onProfileClicked: () -> Unit,
    navigateToItemDetails: (String) -> Unit,
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToCartScreen: () -> Unit

) {
    val homeScreenState = rememberSaveable { mutableStateOf(BottomNavType.Home) }




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


    fun onMenuCategoryButtonClicked(categoryId: Int){
        selectedMenuCategory = categoryId
    }

    val categoryButtons = menCatButtons.fold(
        mutableListOf(MenuCatButtonModel(menuName = "All Items", menuCat = ALL_MENU_CAT_ID,  sortOrder = 0, ))
    ) { acc, curr -> acc.apply { add(curr) } }

    NavigationDrawer(
        drawerState = drawerState,
        onSignOutClicked = onSignOutClicked,
    ) {

        Scaffold(
            topBar = {
                HomeScreenTopBar(
                    onProfileClicked = onProfileClicked,
                    navigateToCartScreen = navigateToCartScreen
                )
            },
            bottomBar = {
                StoveBottomAppBar(
                    navController = navController,
                    homeScreenState = homeScreenState
                )
            }
        ) {contentPadding->

                LazyColumn(
                    modifier = Modifier
                        .padding( contentPadding)
                ){

                    item{
                        FlashCard(restaurantName = if (venueInfo.isNotEmpty()) venueInfo[0].venueName else "Restaurant Name",)
                    }
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    if(menuItems.isEmpty()){
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
                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                            items(menuCategoryData()){ menuCategory->
                                if(
                                    menuCategory.id == selectedMenuCategory ||
                                    selectedMenuCategory == ALL_MENU_CAT_ID
                                ){
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

        }







@Composable
fun MenuCategoryButtonRow(
    menuCategories: List<MenuCatButtonModel> = emptyList(),
    selectedMenuCategory: Int = ALL_MENU_CAT_ID,
    onMenuCategoryButtonClicked: (Int) -> Unit

) {

    ScrollableTabRow(
        selectedTabIndex = menuCategories.indexOfFirst { it.menuCat == selectedMenuCategory },
        edgePadding = 4.dp,
        indicator = {},
        divider = {},
    ) {
        menuCategories.forEach { menuCategory ->
            MenuCategoryButton(
                text = menuCategory.menuName,
                backgroundColor = if (menuCategory.menuCat == selectedMenuCategory) {
                    MaterialTheme.colorScheme.primary
                } else MaterialTheme.colorScheme.surface,
                textColor = if (menuCategory.menuCat == selectedMenuCategory) {
                    MaterialTheme.colorScheme.onPrimary
                } else MaterialTheme.colorScheme.onSurface,
                onClick = { onMenuCategoryButtonClicked(menuCategory.menuCat) }
            )
        }
    }


}


