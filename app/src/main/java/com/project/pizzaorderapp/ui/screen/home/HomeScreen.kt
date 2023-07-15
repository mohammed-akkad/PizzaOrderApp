package com.project.pizzaorderapp.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.pizzaorderapp.ui.screen.home.compose.ItemPizzaIcon
import com.project.pizzaorderapp.ui.screen.home.compose.LargeCardPizza
import com.project.pizzaorderapp.ui.screen.home.compose.MediumCardPizza
import com.project.pizzaorderapp.ui.screen.home.compose.PizzaPager
import com.project.pizzaorderapp.ui.screen.home.compose.PlateImage
import com.project.pizzaorderapp.ui.screen.home.compose.SmallCardPizza
import com.project.pizzaorderapp.ui.theme.DarkGray
import com.project.pizzaorderapp.ui.screen.home.view_model.HomeUiState
import com.project.pizzaorderapp.ui.screen.home.view_model.HomeViewModel
import com.project.pizzaorderapp.ui.screen.home.view_model.IngredientUiState
import com.project.pizzaorderapp.ui.screen.home.view_model.TypeSize

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    HomeContent(
        state,
        onSizeSelected = viewModel::selectedSize,
        onClickIngredient = viewModel::isSelectedIngredient,
        onBreadSelected = viewModel::selectedBread
    )
}

@Composable
fun HomeContent(
    state: HomeUiState,
    onSizeSelected: (TypeSize) -> Unit,
    onClickIngredient: (IngredientUiState) -> Unit,
    onBreadSelected: (Int) -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (plate, breads, textPrice, cardPricePizza, textCustomizePizza, imageCustomizePizza, buttonAddToCart) = createRefs()
            PlateImage(modifier = Modifier
                .width(275.dp)
                .padding(top = 80.dp)
                .constrainAs(plate) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            PizzaPager(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp)
                .wrapContentHeight()
                .constrainAs(breads) {
                    start.linkTo(plate.start)
                    end.linkTo(plate.end)
                    top.linkTo(plate.top)
                    bottom.linkTo(plate.bottom)
                }, state,
                onBreadSelected = onBreadSelected
            )

            Text(
                text = "$17", modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 24.dp)
                    .constrainAs(textPrice) { top.linkTo(plate.bottom) },
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black
            )
            Row(
                modifier = Modifier.constrainAs(cardPricePizza) {
                    top.linkTo(textPrice.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                horizontalArrangement = Arrangement.spacedBy(
                    8.dp,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                SmallCardPizza(state, onSizeSelected)
                MediumCardPizza(state, onSizeSelected)
                LargeCardPizza(state, onSizeSelected)
            }

            Text(
                text = "CUSTOMIZE YOUR PIZZA",
                color = Color.Black.copy(alpha = 0.38f),
                fontSize = 14.sp,
                modifier = Modifier
                    .constrainAs(textCustomizePizza) {
                        start.linkTo(parent.start)
                        top.linkTo(cardPricePizza.bottom)
                    }
                    .padding(start = 16.dp, top = 32.dp))

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(imageCustomizePizza) {
                        top.linkTo(textCustomizePizza.bottom)
                    }
                    .padding(top = 32.dp, bottom = 64.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(
                    16.dp,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                items(state.ingredientUiState) {
                    ItemPizzaIcon(onClickIngredient = onClickIngredient, it)
                }
            }
            Button(
                onClick = { }, modifier = Modifier
                    .wrapContentWidth()
                    .height(48.dp)
                    .constrainAs(buttonAddToCart) {
                        top.linkTo(imageCustomizePizza.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                colors = ButtonDefaults.buttonColors(DarkGray),
                contentPadding = PaddingValues(horizontal = 24.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Add to cart",
                    color = Color.White.copy(alpha = 0.87f),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MyPreview() {
//    Ingredients(items = basil, size = 165.dp)
}
