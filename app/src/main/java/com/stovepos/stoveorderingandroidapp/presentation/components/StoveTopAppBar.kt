package com.stovepos.stoveorderingandroidapp.presentation.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoveTopAppBar(
    title: Int,
    isMainScreen: Boolean = true,
    onBackButtonClicked: () -> Unit = {},
    hasActionIcon:Boolean = false,
    actionIcon : ImageVector? = null,
) {


    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = title),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        modifier = Modifier
            .background(color = Color.LightGray),
        navigationIcon = {
            if(isMainScreen){
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Icon")

                }
            }else{

                IconButton(onClick = { onBackButtonClicked.invoke() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Icon")

                }
            }

        },

        actions = {
            if(hasActionIcon && actionIcon != null){
                IconButton(onClick = {  }) {
                    Icon(imageVector = actionIcon, contentDescription = "Settings Icon")

                }
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface,
            titleContentColor = MaterialTheme.colorScheme.primary

        )
    )





}