package com.stovepos.stoveorderingandroidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.stovepos.stoveorderingandroidapp.ui.theme.StoveOrderingAndroidAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            StoveOrderingAndroidAppTheme {

            }
        }
    }
}

