package com.stovepos.stoveorderingandroidapp.presentation.screens.cart

import androidx.compose.material3.Button
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CartScreenBottomBar() {

    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier,
        shape = Shapes().small
    ) {
        Text(text = "CHECKOUT  ($ 120)")
    }

}