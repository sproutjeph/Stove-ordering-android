package com.stovepos.stoveorderingandroidapp.presentation.screens.authentication

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.MessageBarState
import com.stevdzasan.onetap.OneTapSignInState
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stovepos.stoveorderingandroidapp.R
import com.stovepos.stoveorderingandroidapp.presentation.components.AuthProviderButton
import com.stovepos.stoveorderingandroidapp.presentation.components.StoveTopAppBar
import com.stovepos.stoveorderingandroidapp.presentation.screens.authentication.AuthScreenContent
import com.stovepos.stoveorderingandroidapp.utils.Constants.CLIENT_ID

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationScreen(
    onBackButtonClicked: () -> Unit,
    navigateToAuthWithEmail: () -> Unit,
    authenticated: Boolean,
    loadingState: Boolean,
    oneTapState: OneTapSignInState,
    messageBarState: MessageBarState,
    onTokenIdReceived: (String) -> Unit,
    onDialogDismissed: (String) -> Unit,
    onButtonClicked: () -> Unit,
    navigateToHome: () -> Unit,

    ) {

    Scaffold(
        topBar ={
            StoveTopAppBar(
                title = R.string.register_text,
                isMainScreen = false,
                onBackButtonClicked = onBackButtonClicked

            )
        }
    ) {contentPadding->
        ContentWithMessageBar(messageBarState = messageBarState) {
            AuthScreenContent(
                contentPadding = contentPadding,
                loadingState = loadingState,
                navigateToAuthWithEmail = navigateToAuthWithEmail,
                onButtonClicked = onButtonClicked,
            )
        }

        }

    OneTapSignInWithGoogle(
        state = oneTapState,
        clientId = CLIENT_ID,
        onTokenIdReceived = { tokenId ->
            onTokenIdReceived(tokenId)

        },
        onDialogDismissed = { message ->
            onDialogDismissed(message)

        }
    )

    LaunchedEffect(key1 = authenticated ){
        if(authenticated){
            navigateToHome()
        }
    }

    }

