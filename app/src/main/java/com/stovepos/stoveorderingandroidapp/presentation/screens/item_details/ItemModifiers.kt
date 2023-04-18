package com.stovepos.stoveorderingandroidapp.presentation.screens.item_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.Option

@Composable
fun ItemModifierTitle(
    title: String = "Agave Choice"
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray),
        contentAlignment = Alignment.Center,

        ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(vertical = 8.dp),
            color = Color.Black
        )
    }


}


@Composable
fun ItemModifier(
    modifier: Modifier = Modifier
        .padding(vertical = 4.dp),
    modItem: Option,
    onClick: (itemMod: Option) -> Unit = {}
    ) {

    val isSelected = remember { mutableStateOf(modItem.isSelected) }

    OutlinedButton(
        onClick = { isSelected.value = !isSelected.value; onClick(modItem) },
        modifier = Modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = if (isSelected.value) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
            containerColor = if (isSelected.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        ),

        shape = RoundedCornerShape(4.dp)


    ) {
        modItem.name?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "+ \$${modItem.price}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
        )
    }


}

//@Preview(showBackground = true)
@Composable
fun ItemModifierOptionType3(
    modItem: Option,

    ) {
    var selectedOption by remember { mutableStateOf(modItem.name) }

    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .selectable(
                    selected = false,
                    onClick = { }
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedOption == modItem.name,
                onClick = {
                    selectedOption = modItem.name.toString()
                }
            )
                Text(
                    text = modItem.name ?: "Option 1",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Text(
                    text = "+ \$${modItem.price}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 16.dp)
                )

        }

        Divider()
    }


}


