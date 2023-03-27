package com.stovepos.stoveorderingandroidapp.presentation.screens.home

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
import com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.MenuCat
import com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.MenuItem
import com.stovepos.stoveorderingandroidapp.presentation.components.*

const val ALL_MENU_CAT_ID = 1111


//@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    drawerState: DrawerState,
    onSignOutClicked: () -> Unit,
    onDeleteAllClicked: () -> Unit = {},
    onProfileClicked: () -> Unit,
    navigateToItemDetails: (String) -> Unit ={},
    homeViewModel: HomeViewModel = hiltViewModel()

) {

    val menuDataState = homeViewModel.menuDataState.value
    val venueInfoState = homeViewModel.venueInfoState.value


    var selectedMenuCategory by remember {
        mutableStateOf(ALL_MENU_CAT_ID)
    }




    fun menuCategoryData(): List<MenuCategory>? {
        return menuDataState.menuData?.menuCat?.map { item ->
            MenuCategory(
                id = item.menucat,
                text = item.menu_name,
                numberOfItems = menuDataState.menuData.menuItems.filter { item2 ->
                    item2.menucatid == item.menucat
                }.size,
                items = menuDataState.menuData.menuItems.filter { item2 ->
                    item2.menucatid == item.menucat
                }
            )
        }
    }



    fun onMenuCategoryButtonClicked(categoryId: Int){
        selectedMenuCategory = categoryId
    }

    val categoryButtons = menuDataState.menuData?.menuCat?.fold(
        mutableListOf(MenuCat(menu_name = "All Items", ALL_MENU_CAT_ID,  sortorder = 0, ))
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
                StoveBottomAppBar()
            }
        ) {contentPadding->

                LazyColumn(
                    modifier = Modifier
                        .padding(contentPadding)
                ){

                    item{
                        FlashCard(restaurantName = venueInfoState.venueInfo?.get(0)?.venuename ?: "Restaurant")
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
                            categoryButtons?.let {
                                MenuCategoryButtonRow(
                                    menuCategories = it,
                                    selectedMenuCategory = selectedMenuCategory,
                                    onMenuCategoryButtonClicked = { onMenuCategoryButtonClicked(it) }
                                )
                            }

                        }


                        items(menuCategoryData() as List<MenuCategory>){MenuCategory->
                            if(MenuCategory.id == selectedMenuCategory || selectedMenuCategory == ALL_MENU_CAT_ID){
                                MenuCategory.items.forEach {
                                    MenuItem(
                                        itemName = it.item_name,
                                        itemPrice = it.item_price,
                                        itemDesc = it.item_name,
                                        onMenuItemClicked = { navigateToItemDetails(it.itemid.toString()) }
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
    menuCategories: List<MenuCat> = emptyList(),
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
                    text = menuCategory.menu_name,
                    backgroundColor = if(menuCategory.menucat == selectedMenuCategory) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                    textColor = if(menuCategory.menucat == selectedMenuCategory) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                    onClick = { onMenuCategoryButtonClicked(menuCategory.menucat) }
                )

            }

        }
    }



}


class MenuCategory(
    val id: Int,
    val text: String,
    val numberOfItems: Int,
    val items: List<MenuItem>
)