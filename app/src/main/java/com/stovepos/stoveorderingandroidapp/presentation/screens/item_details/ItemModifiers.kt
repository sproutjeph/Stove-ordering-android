package com.stovepos.stoveorderingandroidapp.presentation.screens.item_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    onItemModifierClicked: () -> Unit = {},
    modItem: Option,
    ) {


    OutlinedButton(
        onClick={ onItemModifierClicked()},
        modifier = Modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = if(modItem.isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
            containerColor = if(modItem.isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        ),

        shape = RoundedCornerShape(4.dp)




    ) {
        Text(
            text = modItem.name,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
        )
        Spacer(modifier =  Modifier.weight(1f))
        Text(
            text = "+ \$${modItem.price}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
        )
    }


}


