package com.stovepos.stoveorderingandroidapp.data.mongo_db

import com.stovepos.stoveorderingandroidapp.utils.Constants.APP_ID
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId

object  MongoDB : MongoRepository {

    private val app = App.create(APP_ID)
    private val user = app.currentUser
    private lateinit var realm: Realm

    init {
        configureTheRealm()
    }


    override fun configureTheRealm() {
        if (user != null) {
            val config = SyncConfiguration.Builder(
                user,
                setOf(CartItem::class, OptionRealm::class)
            ).initialSubscriptions { sub ->
                add(
                    query = sub.query<CartItem>(query = "owner_id == $0", user.id),
                    name = "User CartItems"
                )
            }
                .log(LogLevel.ALL)
                .build()

            realm = Realm.open(config)
        }

    }

    override fun getAllCartItems(): Flow<AllCartItems> {
        return if (user != null) {
            try {
                realm.query<CartItem>(query = "owner_id == $0", user.id)
                    .asFlow()
                    .map { result ->
                        RequestState.Success(
                            data = result.list
                        )

                    }

            } catch (e: Exception) {
                flow { emit(RequestState.Error(e)) }

            }
        } else {
            flow { emit(RequestState.Error(UserNotAuthenticatedException())) }

        }
    }

    override suspend fun insertCartItem(cartItem: CartItem): RequestState<CartItem> {
        return if (user != null) {
            realm.write {
                try {
                    val addedCartItem = copyToRealm(cartItem.apply { owner_id = user.id })
                    RequestState.Success(data = addedCartItem)
                } catch (e: Exception) {
                    RequestState.Error(e)

                }
            }
        } else {
            RequestState.Error(UserNotAuthenticatedException())

        }
    }

    override suspend fun updateCartItem(
        cartItem: CartItem,
        mod: OptionRealm
    ): RequestState<CartItem> {
        return if (user != null) {
            realm.write {
                val queriedCartItem = query<CartItem>(query = "_id == $0", cartItem._id)
                    .first().find()

                if (queriedCartItem != null) {
                    queriedCartItem.itemMods.remove(mod)
                    RequestState.Success(data = queriedCartItem)
                } else {
                    RequestState.Error(error = Exception("Queried Diary does not exist."))
                }
            }
        } else {
            RequestState.Error(UserNotAuthenticatedException())

        }
    }

    override suspend fun deleteCartItem(id: ObjectId): RequestState<Boolean> {
        return if (user != null) {
            realm.write {
                val cartItem = query<CartItem>(query = "_id == $0 AND owner_id ==$1", id, user.id)
                    .first().find()
                if (cartItem != null) {
                    try {
                        delete(cartItem)
                        RequestState.Success(data = true)
                    } catch (e: Exception) {
                        RequestState.Error(e)

                    }
                } else {
                    RequestState.Error(Exception("CartItem does not exist."))

                }

            }
        } else {
            RequestState.Error(UserNotAuthenticatedException())

        }


    }

    override suspend fun deleteAllCartItems(): RequestState<Boolean> {
        return if (user != null) {
            realm.write {
                val cartItems = this.query<CartItem>(query = "_id == $0, user.id").find()
                try {
                    delete(cartItems)
                    RequestState.Success(data = true)
                } catch (e: Exception) {
                    RequestState.Error(e)

                }
            }
        } else {
            RequestState.Error(UserNotAuthenticatedException())

        }
    }
}

private class UserNotAuthenticatedException : Exception("User is not Logged in.")