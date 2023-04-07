package com.stovepos.stoveorderingandroidapp.presentation.screens.item_details

import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stovepos.stoveorderingandroidapp.data.local.MenuItemModel
import com.stovepos.stoveorderingandroidapp.data.mongo_db.CartItem
import com.stovepos.stoveorderingandroidapp.data.mongo_db.MongoDB
import com.stovepos.stoveorderingandroidapp.data.mongo_db.RequestState
import com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.Option
import com.stovepos.stoveorderingandroidapp.repository.MenuItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ItemDetailsViewModel @Inject constructor (
    private val menuItemsRepository: MenuItemsRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _menuItem = mutableStateOf<MenuItemModel?>(null)

    val menuItem = _menuItem


    init {
        savedStateHandle.get<String>("itemId")?.let {
            getSelectedMenuItemById(it)
        }


    }



    private fun getSelectedMenuItemById(itemId: String){
        viewModelScope.launch(Dispatchers.IO) {
            _menuItem.value =   menuItemsRepository.getMenuItemById(itemId.toInt())
        }
    }

     suspend fun addToCart(
        cartItem:CartItem,
        onSuccess: ()-> Unit,
        onError:(String) -> Unit
    ){
        val result = MongoDB.insertCartItem(cartItem = cartItem)
        if(result is RequestState.Success){
            withContext(Dispatchers.Main){
                onSuccess()
            }
        }else if(result is RequestState.Error){
            withContext(Dispatchers.Main){
                onError(result.error.message.toString())
            }
        }

    }

}