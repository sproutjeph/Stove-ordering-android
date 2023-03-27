package com.stovepos.stoveorderingandroidapp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//@Preview(showBackground = true)
@Composable
fun MenuCategoryButton(
    text: String = "All",
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    textColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {

    OutlinedButton(
        onClick = { onClick() },
        modifier = Modifier
            .height(34.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = textColor,
            containerColor = backgroundColor

        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 1.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        ),
        border = BorderStroke(
            width = 1.dp,
            brush = Brush.horizontalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.surface,
                    MaterialTheme.colorScheme.surface,

                    )
            )
        ),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}