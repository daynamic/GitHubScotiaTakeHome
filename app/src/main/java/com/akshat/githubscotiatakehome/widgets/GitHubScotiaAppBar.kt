package com.akshat.githubscotiatakehome.widgets

import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GitHubScotiaAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
) {
    Surface(shadowElevation = elevation) {
        TopAppBar(
            title = {
                Text(
                    text = title, color = Color.White, style = TextStyle(
                        fontWeight = FontWeight.Bold, fontSize = 17.sp
                    )
                )
            },
            navigationIcon = {
                if (icon != null && !isMainScreen) {
                    Icon(imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color(
                    red = 75, green = 95, blue = 188
                )
            ),
        )
    }
}