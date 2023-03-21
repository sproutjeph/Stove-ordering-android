package com.stovepos.stoveorderingandroidapp.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun MenuCategoryButton(
    text: String = "All",
    onClick: (menuCategoryId: Int) -> Unit = { }
) {

    OutlinedButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .height(34.dp),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}