package com.stovepos.stoveorderingandroidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.stovepos.stoveorderingandroidapp.navigation.Screen
import com.stovepos.stoveorderingandroidapp.navigation.SetupNavigation
import com.stovepos.stoveorderingandroidapp.ui.theme.StoveOrderingAndroidAppTheme
import com.stovepos.stoveorderingandroidapp.utils.Constants.APP_ID
import dagger.hilt.android.AndroidEntryPoint
import io.realm.kotlin.mongodb.App
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass
            StoveOrderingAndroidAppTheme {
                val navController = rememberNavController()
                SetupNavigation(
                    startDestination = getStartDestination(),
                    navController = navController,
                    widthSizeClass = widthSizeClass

                )

            }
        }
    }
}

private fun getStartDestination(): String{
      val user = App.create(APP_ID).currentUser
      return if((user != null) && user.loggedIn)  Screen.Home.route else Screen.LogoSplash.route
}

