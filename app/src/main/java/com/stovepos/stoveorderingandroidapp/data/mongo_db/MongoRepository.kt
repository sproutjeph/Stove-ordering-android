package com.stovepos.stoveorderingandroidapp.data.mongo_db

import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

typealias AllCartItems = RequestState<List<CartItem>>

interface MongoRepository {
    fun configureTheRealm()
    fun getAllCartItems(): Flow<AllCartItems>
    suspend fun insertCartItem(cartItem: CartItem):RequestState<CartItem>
    suspend fun updateCartItem(cartItem: CartItem, mod: OptionRealm):RequestState<CartItem>
    suspend fun deleteCartItem(id: ObjectId):RequestState<Boolean>
    suspend fun deleteAllCartItems():RequestState<Boolean>
}