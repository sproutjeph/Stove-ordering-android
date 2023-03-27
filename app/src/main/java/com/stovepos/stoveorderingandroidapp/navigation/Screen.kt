package com.stovepos.stoveorderingandroidapp.navigation

sealed class Screen(val route: String){
    object Authentication: Screen("authentication_screen")
    object AuthWithEmail: Screen("auth_with_email_screen")
    object LogoSplash: Screen("logo_splash_screen")
    object WelcomeSplash: Screen("welcome_splash_screen")
    object OrderSplash: Screen("order_splash_screen")
    object PaymentSplash: Screen("payment_splash_screen")
    object DeliverySplash: Screen("delivery_splash_screen")
    object Home: Screen("home_screen")
    object ItemDetails: Screen("item_details_screen?itemId={itemId}"){
        fun passItemId(itemId: String): String{
            return "item_details_screen?itemId=$itemId"
        }
    }

}
