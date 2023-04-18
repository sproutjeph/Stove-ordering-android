package com.stovepos.stoveorderingandroidapp.presentation.screens.cart

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stovepos.stoveorderingandroidapp.data.mongo_db.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class CartScreenViewModel(
    private val savedStateHandle: SavedStateHandle
) :ViewModel() {

    var cartItemId = savedStateHandle.get<String>("itemId")

    var id = cartItemId?.split("(")?.get(1)?.split(")")?.get(0)

    val itemId = id?.let { ObjectId.invoke(it) }

    var selectedCartItem: MutableState<CartItem?> = mutableStateOf(null)

    var cartItems: MutableState<AllCartItems> = mutableStateOf(RequestState.Idle)

    var numberOfItemInCart by mutableStateOf(0)
    var totalPrice by mutableStateOf(0.0)

    init {
        getCartItems()
        itemId?.let { ObjectId.invoke(it.toHexString()) }?.let { getCartItemById(it) }
    }


    private fun getCartItems(){
        cartItems.value = RequestState.Loading

         viewModelScope.launch {
            MongoDB.getAllCartItems().collect(){result->
                cartItems.value = result

                if(result is RequestState.Success){
                    numberOfItemInCart = result.data.fold(0){acc, cartItem ->
                        acc + cartItem.itemQty
                    }

                    totalPrice = result.data.fold(0.0){
                        acc, cartItem ->
                        acc + cartItem.itemPrice.toDouble()
                    }
                }

            }
        }
    }

    fun deleteCartItem(cartItemId: ObjectId){
        viewModelScope.launch {
            MongoDB.deleteCartItem(id = cartItemId)

        }
    }

    fun upDateCartItem(cartItemId: ObjectId, mod: OptionRealm){
        viewModelScope.launch {
            MongoDB.updateCartItem(cartItemId = cartItemId, mod = mod)
        }

    }

    private fun getCartItemById(cartItemId: ObjectId){
        viewModelScope.launch {
            MongoDB.getCartItemById(cartItemId).catch {
                emit(RequestState.Error(Exception("Cart Item is already deleted.")))
            }
                .collect{item->
                if(item is RequestState.Success){
                    selectedCartItem.value = item.data

                }

            }
        }


    }
}