package com.stovepos.stoveorderingandroidapp.presentation.screens.home


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stovepos.stoveorderingandroidapp.network.GetMenuData
import com.stovepos.stoveorderingandroidapp.network.GetVenueInfo
import com.stovepos.stoveorderingandroidapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor (
    private val getMenuDataNetwork: GetMenuData,
    private val getVenueInfoNetwork: GetVenueInfo,
    savedStateHandle: SavedStateHandle
        ): ViewModel() {

    private val _menuDataState = mutableStateOf(MenuDataState())
    val menuDataState: State<MenuDataState> = _menuDataState

    private val _venueInfoState = mutableStateOf(VenueInfoState())

      val venueInfoState: State<VenueInfoState> = _venueInfoState






    init {
        getMenuData()
        getVenueInfo()
    }

    private fun getMenuData(){

        getMenuDataNetwork().onEach {result->

        when(result){
            is Resource.Success -> {
                _menuDataState.value = MenuDataState(
                    menuData = result.data
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
            else -> {}
        }

        }.launchIn(viewModelScope)
    }

    private fun getVenueInfo(){

        getVenueInfoNetwork().onEach {result->

        when(result){
            is Resource.Success -> {
                _venueInfoState.value = VenueInfoState(
                    venueInfo = result.data

                )

            }
            is Resource.Error -> {
                _venueInfoState.value = VenueInfoState(
                    error = result.message ?: "An unexpected error occurred"
                )
            }
            is Resource.Loading -> {
                _venueInfoState.value =VenueInfoState(
                    isLoading = true
                )
            }
            else -> {}
        }

        }.launchIn(viewModelScope)

    }

}








