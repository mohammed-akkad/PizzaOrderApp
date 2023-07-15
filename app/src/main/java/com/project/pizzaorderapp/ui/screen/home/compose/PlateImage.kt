package com.project.pizzaorderapp.ui.screen.home.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.project.pizzaorderapp.R
import com.project.pizzaorderapp.ui.screen.compose.CustomImage

@Composable
fun PlateImage(modifier: Modifier = Modifier) {
    CustomImage(
        painter = painterResource(id = R.drawable.plate),
        modifier = modifier
    )
}