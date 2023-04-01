package com.stovepos.stoveorderingandroidapp.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.stevdzasan.messagebar.rememberMessageBarState
import com.stevdzasan.onetap.rememberOneTapSignInState
import com.stovepos.stoveorderingandroidapp.R
import com.stovepos.stoveorderingandroidapp.presentation.components.DisplayAlertDialog
import com.stovepos.stoveorderingandroidapp.presentation.components.SplashScreenCommon
import com.stovepos.stoveorderingandroidapp.presentation.screens.authentication.AuthenticationScreen
import com.stovepos.stoveorderingandroidapp.presentation.screens.authentication.AuthWithEmailScreen
import com.stovepos.stoveorderingandroidapp.presentation.screens.authentication.AuthenticationViewModel
import com.stovepos.stoveorderingandroidapp.presentation.screens.home.HomeScreen
import com.stovepos.stoveorderingandroidapp.presentation.screens.item_details.ItemDetailsScreen
import com.stovepos.stoveorderingandroidapp.presentation.screens.restaurents.RestaurantsScreen
import com.stovepos.stoveorderingandroidapp.presentation.screens.splash_screens.LogoSplashScreen
import com.stovepos.stoveorderingandroidapp.presentation.screens.splash_screens.WelcomeSplashScreen
import com.stovepos.stoveorderingandroidapp.utils.Constants.APP_ID
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun SetupNavigation(
    startDestination: String,
    navController: NavHostController,
) {

    NavHost(
        startDestination = startDestination,
        navController = navController,
    ){
        logoSplashRoute(
            navigateToNextSplashScreen = {
                navController.navigate(Screen.WelcomeSplash.route)

            },

        )

        welcomeSplashRoute(
            navigateToNextSplashScreen = {
                navController.navigate(Screen.OrderSplash.route)
            }
        )

        orderSplashRoute(
            navigateToNextSplashScreen = {
                navController.navigate(Screen.PaymentSplash.route)
            }
        )

        paymentSplashRoute(
            navigateToNextSplashScreen = {
                navController.navigate(Screen.DeliverySplash.route)
            }
        )

        deliverySplashRoute(
            navigateToNextSplashScreen = {
                navController.navigate(Screen.Authentication.route)
            }
        )

        authenticationRoute(
            navigateToAuthWithEmail = {
                navController.navigate(Screen.AuthWithEmail.route)
            },
            onBackButtonClicked = {
                navController.popBackStack()
            },
            navigateToHome = {
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            },
        )
        authWithEmail(
            onBackButtonClicked = {
                navController.popBackStack()
            }
        ){}

        homeRoute(
            navigateToAuth = {
                navController.popBackStack()
                navController.navigate(Screen.Authentication.route)
            },
            navigateToItemDetails = {
                navController.navigate(Screen.ItemDetails.passItemId(itemId = it))
            },
            navController = navController
        )

        itemDetailRoute(
            onBackPressed = {
                navController.popBackStack()
            }
        )

        restaurantsRoute(
            onBackPressed = {
                navController.popBackStack()
            },
            onRestaurantClick = {
                navController.navigate(Screen.Home.route)
            }
        )
    }



}




fun NavGraphBuilder.logoSplashRoute(
    navigateToNextSplashScreen: () -> Unit,

){
    composable(route = Screen.LogoSplash.route){

        LogoSplashScreen(
            navigateToNextSplashScreen = navigateToNextSplashScreen,
        )

    }
}

fun NavGraphBuilder.welcomeSplashRoute(
    navigateToNextSplashScreen: () -> Unit
){
    composable(route = Screen.WelcomeSplash.route){
        WelcomeSplashScreen(navigateToNextSplashScreen = navigateToNextSplashScreen)

    }
}

fun NavGraphBuilder.orderSplashRoute(
    navigateToNextSplashScreen: () -> Unit

){
    composable(route = Screen.OrderSplash.route){
        SplashScreenCommon(
            imageId =  R.drawable.splash_screen_order,
            imageDes = R.string.order_image_des,
            title = R.string.order_splash_screen_title,
            description = R.string.order_splash_screen_text,
            onNextButtonClicked = navigateToNextSplashScreen
        )

    }
}

fun NavGraphBuilder.paymentSplashRoute(
    navigateToNextSplashScreen: () -> Unit
){
    composable(route = Screen.PaymentSplash.route){
        SplashScreenCommon(
            imageId = R.drawable.splash_screen_payment,
            imageDes = R.string.payment_image_des,
            title = R.string.payment_splash_screen_title,
            description = R.string.payment_splash_screen_text,
            onNextButtonClicked = navigateToNextSplashScreen
        )

    }
}

fun NavGraphBuilder.deliverySplashRoute(
    navigateToNextSplashScreen: () -> Unit
){
    composable(route = Screen.DeliverySplash.route){
        SplashScreenCommon(
            imageId = R.drawable.splash_screen_delivery,
            imageDes = R.string.delivery_image_des,
            title = R.string.delivery_splash_screen_title,
            description = R.string.delivery_splash_screen_text,
            onNextButtonClicked = navigateToNextSplashScreen
        )

    }
}



fun NavGraphBuilder.authenticationRoute(
    onBackButtonClicked: () -> Unit,
    navigateToAuthWithEmail: () -> Unit,
    navigateToHome: () -> Unit,
){
    composable(route = Screen.Authentication.route){
        val authenticationViewModel: AuthenticationViewModel = viewModel()
        val authenticated by authenticationViewModel.authenticated
        val loadingState by authenticationViewModel.loadingState
        val oneTapState = rememberOneTapSignInState()
        val messageBarState = rememberMessageBarState()


        AuthenticationScreen(
            authenticated = authenticated,
            loadingState = loadingState,
            oneTapState = oneTapState,
            onBackButtonClicked = onBackButtonClicked,
            navigateToAuthWithEmail = navigateToAuthWithEmail,
            onButtonClicked = {
                oneTapState.open()
                authenticationViewModel.setLoadingState(true)
            },
            messageBarState = messageBarState,
            onTokenIdReceived = {tokenId ->
                authenticationViewModel.signInWithMongoAtlas(
                    tokenId = tokenId,
                    onSuccess = {
                        messageBarState.addSuccess("Successfully signed in")
                        authenticationViewModel.setLoadingState(false)
                    },
                    onError = {exception ->
                        messageBarState.addError(exception)
                        authenticationViewModel.setLoadingState(false)

                    }
                )

            },

            onDialogDismissed = {message->
                messageBarState.addError(Exception(message))
                authenticationViewModel.setLoadingState(false)
            },
            navigateToHome = navigateToHome
        )

    }
}

fun NavGraphBuilder.authWithEmail(
    onBackButtonClicked: () -> Unit,
    navigateToHome: () -> Unit
){
    composable(route = Screen.AuthWithEmail.route){
        AuthWithEmailScreen(
            onBackButtonClicked = onBackButtonClicked,

            )

    }
}



fun NavGraphBuilder.homeRoute(
    navigateToAuth: () -> Unit,
    navController: NavController,
    navigateToItemDetails: (String) -> Unit,
    ){
    composable(route = Screen.Home.route){
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val context = LocalContext.current
        var signOutDialogOpened by remember { mutableStateOf(false) }


        HomeScreen(
            drawerState = drawerState,
            onProfileClicked = {
                scope.launch { drawerState.open() }
            },
            onSignOutClicked = {
                signOutDialogOpened = true
            },
            navigateToItemDetails = navigateToItemDetails,
            navController = navController

        )

        DisplayAlertDialog(
            title = "Sign Out",
            message = "Are you sure you want to Sign Out from your Google Account?",
            dialogOpened = signOutDialogOpened,
            onDialogClosed = { signOutDialogOpened = false },
            onYesClicked = {
                scope.launch(Dispatchers.IO) {
                    val user = App.create(APP_ID).currentUser
                    if (user != null) {
                        user.logOut()
                        withContext(Dispatchers.Main) {
                            navigateToAuth()
                        }
                    }
                }
            }
        )

    }
}


fun NavGraphBuilder.itemDetailRoute(
    onBackPressed: ()-> Unit

) {
    composable(
        route = Screen.ItemDetails.route,
        arguments = listOf(navArgument(name = "itemId"){
            type = NavType.StringType

        })
    ) {
        val itemId = it.arguments?.getString("itemId")
        ItemDetailsScreen (
                itemId = itemId ?: "null",
                onBackPressed = onBackPressed
        )

    }

}

fun NavGraphBuilder.restaurantsRoute(
    onBackPressed: ()-> Unit,
    onRestaurantClick: () -> Unit

) {
    composable(route = Screen.Restaurants.route,){
        RestaurantsScreen(
            onBackPressed = onBackPressed,
            onRestaurantClick = onRestaurantClick
        )

    }

}