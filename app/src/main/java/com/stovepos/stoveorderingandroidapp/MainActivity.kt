package com.stovepos.stoveorderingandroidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.stovepos.stoveorderingandroidapp.navigation.Screen
import com.stovepos.stoveorderingandroidapp.navigation.SetupNavigation
import com.stovepos.stoveorderingandroidapp.ui.theme.StoveOrderingAndroidAppTheme
import com.stovepos.stoveorderingandroidapp.utils.Constants.APP_ID
import dagger.hilt.android.AndroidEntryPoint
import io.realm.kotlin.mongodb.App

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            StoveOrderingAndroidAppTheme {
                val navController = rememberNavController()
                SetupNavigation(
                    startDestination = getStartDestination(),
                    navController = navController,

                )

            }
        }
    }
}

private fun getStartDestination(): String{
      val user = App.create(APP_ID).currentUser
      return if(user != null && user.loggedIn)  Screen.Home.route else Screen.LogoSplash.route
}

