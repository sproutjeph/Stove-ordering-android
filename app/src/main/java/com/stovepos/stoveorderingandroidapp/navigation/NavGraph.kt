package com.stovepos.stoveorderingandroidapp.navigation

import com.stovepos.stoveorderingandroidapp.presentation.screens.my_addresses.MyAddressesScreen
import com.stovepos.stoveorderingandroidapp.presentation.screens.my_payment_cards.MyPaymentCardsScreen
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
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
import com.stovepos.stoveorderingandroidapp.presentation.screens.cart.CartItemDetailsScreen
import com.stovepos.stoveorderingandroidapp.presentation.screens.cart.CartScreen
import com.stovepos.stoveorderingandroidapp.presentation.screens.cart.CartScreenViewModel
import com.stovepos.stoveorderingandroidapp.presentation.screens.home.HomeScreen
import com.stovepos.stoveorderingandroidapp.presentation.screens.item_details.ItemDetailsScreen
import com.stovepos.stoveorderingandroidapp.presentation.screens.profile.ProfileScreen
import com.stovepos.stoveorderingandroidapp.presentation.screens.restaurents.RestaurantsScreen
import com.stovepos.stoveorderingandroidapp.presentation.screens.splash_screens.LogoSplashScreen
import com.stovepos.stoveorderingandroidapp.presentation.screens.splash_screens.WelcomeSplashScreen
import com.stovepos.stoveorderingandroidapp.utils.Constants.APP_ID
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.mongodb.kbson.ObjectId

@Composable
fun SetupNavigation(
    startDestination: String,
    navController: NavHostController,
    cartScreenViewModel: CartScreenViewModel = viewModel(),
    widthSizeClass: WindowWidthSizeClass,
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
            navigateToCartScreen = {
                                   navController.navigate(Screen.Cart.route)
            },
            navController = navController
        )

        itemDetailRoute(
            onBackPressed = {
                navController.popBackStack()
            },
            navigateToCartScreen = {
                navController.navigate(Screen.Cart.route)
            },
        )

        restaurantsRoute(
            onBackPressed = {
                navController.popBackStack()
            },
            onRestaurantClick = {
                navController.navigate(Screen.Home.route)
            }
        )

        profileRoute(
            onBackButtonClicked = {
                navController.popBackStack()
            },
            navigateToAddressScreen = {
                navController.navigate(Screen.MyAddress.route)
            },
            navigateToMyPaymentsCardsScreen = {
                navController.navigate(Screen.MyPaymentCards.route)
            }


        )

        myAddressRoute(
            onBackButtonClicked = {
                navController.popBackStack()
            }
        )

        myPaymentsCardsRoute(
            onBackButtonClicked = {
                navController.popBackStack()
            }
        )

        cartRoute(
            onBackButtonClicked = {
                navController.popBackStack()
            },
            navigateToCartItemDetails = {
                navController.navigate(Screen.CartItemDetails.passItemId(itemId = it))

            },
            onDeleteItem = {cartScreenViewModel.deleteCartItem(it)}
        )

        cartItemDetailsRoute(
            onBackButtonClicked = {
                navController.popBackStack()
            },
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
    navigateToCartScreen: () -> Unit,

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
            navigateToCartScreen = navigateToCartScreen,
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
    onBackPressed: ()-> Unit,
    navigateToCartScreen: () -> Unit,

) {
    composable(
        route = Screen.ItemDetails.route,
        arguments = listOf(navArgument(name = "itemId"){
            type = NavType.StringType

        })
    ) {
        val itemId = it.arguments?.getString("itemId")
        ItemDetailsScreen (
            onBackPressed = onBackPressed,
            navigateToCartScreen = navigateToCartScreen,

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

fun NavGraphBuilder.profileRoute(
    onBackButtonClicked: () -> Unit,
    navigateToAddressScreen: ()-> Unit,
    navigateToMyPaymentsCardsScreen: ()-> Unit,

    ){
    composable(route = Screen.Profile.route){
        ProfileScreen(
            onBackButtonClicked = onBackButtonClicked,
            navigateToAddressScreen = navigateToAddressScreen,
            navigateToMyPaymentsCardsScreen = navigateToMyPaymentsCardsScreen
        )

    }
}

fun NavGraphBuilder.myAddressRoute(
    onBackButtonClicked: () -> Unit,

){
    composable(route = Screen.MyAddress.route){
        MyAddressesScreen(
            onBackButtonClicked = onBackButtonClicked
        )

    }
}

fun NavGraphBuilder.myPaymentsCardsRoute(
    onBackButtonClicked: () -> Unit,

    ){
    composable(route = Screen.MyPaymentCards.route){
        MyPaymentCardsScreen(
            onBackButtonClicked = onBackButtonClicked
        )

    }
}

fun NavGraphBuilder.cartRoute(
    onBackButtonClicked: () -> Unit,
    navigateToCartItemDetails: (String) -> Unit,
    onDeleteItem: (id:ObjectId) -> Unit,

){
    composable(route = Screen.Cart.route){
        CartScreen(
            onBackButtonClicked = onBackButtonClicked,
            navigateToCartItemDetails = navigateToCartItemDetails,
            onDeleteItem = onDeleteItem

        )

    }
}

fun NavGraphBuilder.cartItemDetailsRoute(
    onBackButtonClicked: () -> Unit,

){
    composable(
        route = Screen.CartItemDetails.route,
        arguments = listOf(navArgument(name = "itemId"){
            type = NavType.StringType

        })
    ){

            CartItemDetailsScreen(
                onBackPressed = onBackButtonClicked

            )

    }
}









