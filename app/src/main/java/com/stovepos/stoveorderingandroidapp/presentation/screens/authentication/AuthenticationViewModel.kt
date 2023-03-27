package com.stovepos.stoveorderingandroidapp.presentation.screens.authentication

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stovepos.stoveorderingandroidapp.utils.Constants.APP_ID
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthenticationViewModel : ViewModel() {
    var authenticated = mutableStateOf(false)
    var loadingState = mutableStateOf(false)
        private set


    fun setLoadingState(loading: Boolean){
        loadingState.value = loading
    }

    fun signInWithMongoAtlas(
        tokenId: String,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO){
                    App.create(APP_ID).login(
                        Credentials.jwt(tokenId)
                    ).loggedIn
                }

                Log.d("AuthScc", "signInWithMongoAtlas: $result")

                withContext(Dispatchers.Main){
                    if(result){
                        onSuccess()
                        delay(600)
                        authenticated.value = true
                    }else{
                        onError(Exception(Exception("User is not logged in.")))
                    }
                }

            }catch(e: Exception){
                withContext(Dispatchers.Main){
                    onError(e)
                    Log.d("AuthError", "signInWithMongoAtlas: $e")
                }

            }
        }
    }
}