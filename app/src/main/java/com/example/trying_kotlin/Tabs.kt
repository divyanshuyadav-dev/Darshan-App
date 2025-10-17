package com.example.trying_kotlin

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector



@Composable
fun Navos(modifier: Modifier = Modifier) {

    var currentPage by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            it.icon,
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentPage,
                    onClick = { currentPage = it }
                )
            }
        }
    ) {
        Column {
            if (currentPage == AppDestinations.HOME) {
                Cameros()
            }
            else if (currentPage == AppDestinations.HISTORY) {
                Text("History API Call Karni Hai Idhar")
            }
            else if (currentPage == AppDestinations.PROFILE) {
                Text("Profile API Call Karni Hai Idhar")
            }
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    HOME("Home", Icons.Default.Home),
    HISTORY("History", Icons.Default.Book),
    PROFILE("Profile", Icons.Default.AccountBox),
}