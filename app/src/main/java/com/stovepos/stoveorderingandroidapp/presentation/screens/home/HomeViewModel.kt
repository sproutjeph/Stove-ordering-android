package com.stovepos.stoveorderingandroidapp.presentation.screens.home


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stovepos.stoveorderingandroidapp.data.local.MenuCatButtonModel
import com.stovepos.stoveorderingandroidapp.data.local.MenuItemModel
import com.stovepos.stoveorderingandroidapp.data.local.VenueInfoModel
import com.stovepos.stoveorderingandroidapp.network.GetMenuData
import com.stovepos.stoveorderingandroidapp.network.GetVenueInfo
import com.stovepos.stoveorderingandroidapp.repository.MenuItemsRepository
import com.stovepos.stoveorderingandroidapp.repository.VenueInfoLocalRepository
import com.stovepos.stoveorderingandroidapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor (
    private val getMenuDataNetwork: GetMenuData,
    private val getVenueInfoNetwork: GetVenueInfo,
    private val menuItemsRepository: MenuItemsRepository,
    private val venueInfoLocalRepository: VenueInfoLocalRepository,
        ): ViewModel() {

    private val _menuDataState = mutableStateOf(MenuDataState())
    val menuDataState: State<MenuDataState> = _menuDataState


    private val _venueInfo = MutableStateFlow<List<VenueInfoModel>>(emptyList())
    val venueInfo = _venueInfo.asStateFlow()

    private val _menuItems = MutableStateFlow<List<MenuItemModel>>(emptyList())
    val menuItems = _menuItems.asStateFlow()

    private val _menuCatButtons = MutableStateFlow<List<MenuCatButtonModel>>(emptyList())
    val menuCatButtons = _menuCatButtons.asStateFlow()


    init {
        getMenuData()
        getVenueInfo()

        viewModelScope.launch(Dispatchers.IO) {
            menuItemsRepository.getAllMenuItems().distinctUntilChanged()
                .collect {
                    listOfMenuItems ->
                    if(listOfMenuItems.isEmpty()){
                        Log.d("Empty", "Empty List ")
                    }else{
                        _menuItems.value = listOfMenuItems
                    }
                }



        }

        viewModelScope.launch {
            menuItemsRepository.getAllMenuCatButtons().distinctUntilChanged()
                .collect{
                        listOfMenuCatButton ->
                    if(listOfMenuCatButton.isEmpty()){
                        Log.d("Empty", "Empty List ")
                    }else{
                        _menuCatButtons.value = listOfMenuCatButton
                    }
                }
        }

        viewModelScope.launch {
            venueInfoLocalRepository.getAllVenueInfo().distinctUntilChanged()
                .collect{
                        listOfVenueInfo ->
                    if(listOfVenueInfo.isEmpty()){
                        Log.d("Empty", "Empty List ")
                    }else{
                        _venueInfo.value = listOfVenueInfo
                    }
                }

        }
        menuItemsRepository.getAllMenuItems().distinctUntilChanged()
        menuItemsRepository.getAllMenuCatButtons().distinctUntilChanged()
        venueInfoLocalRepository.getAllVenueInfo().distinctUntilChanged()




    }

    private fun getMenuData(){

        getMenuDataNetwork().onEach {result->

        when(result){
            is Resource.Success -> {
                _menuDataState.value = MenuDataState(
                    isLoading = false
                )
            }

            is Resource.Error -> {
                _menuDataState.value = MenuDataState(
                    error = result.message ?: "An unexpected error occurred"
                )
            }

            is Resource.Loading -> {
                _menuDataState.value = MenuDataState(
                    isLoading = true
                )
            }
        }

        }.launchIn(viewModelScope)
    }



    private fun getVenueInfo(){

        getVenueInfoNetwork().onEach {result->

        when(result){
            is Resource.Success -> {}
            is Resource.Error -> {}
            is Resource.Loading -> {}
        }

        }.launchIn(viewModelScope)

    }

}








