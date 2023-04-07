package com.stovepos.stoveorderingandroidapp.presentation.screens.item_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun SpecialRequest() {

    var textInput by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Special Request",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = textInput,
            onValueChange = {textInput = it},
            placeholder = {
                Text(
                    text = "Special request are not guaranteed. it may be ignored",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(4.dp)
        )
    }
}
