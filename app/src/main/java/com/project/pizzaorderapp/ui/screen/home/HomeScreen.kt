package com.project.pizzaorderapp.ui.screen.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.project.pizzaorderapp.R
import com.project.pizzaorderapp.ui.screen.compose.CustomImage
import com.project.pizzaorderapp.ui.theme.DarkGray
import com.project.pizzaorderapp.ui.theme.PaleGreen
import com.project.pizzaorderapp.view_model.HomeUiState
import com.project.pizzaorderapp.view_model.HomeViewModel
import com.project.pizzaorderapp.view_model.IngredientUiState
import com.project.pizzaorderapp.view_model.TypeSize

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

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
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
//                onBreadSelected = onBreadSelected
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaPager(
    modifier: Modifier = Modifier,
    state: HomeUiState,
//    onBreadSelected: (Int) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = state.selectedBreadIndex)
    HorizontalPager(
        pageCount = state.breads.size,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
        state = pagerState,

    ) { index ->
        val transition =
            updateTransition(targetState = state.selectedSizePizza, label = "size")
        val size by transition.animateDp(label = "size") {
            when (it) {
                TypeSize.Small -> 165.dp
                TypeSize.Medium -> 190.dp
                TypeSize.Large -> 230.dp
            }
        }

        Box(modifier = Modifier.size(size), contentAlignment = Alignment.Center) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(state.breads[index])
                    .build(), contentDescription = null,
                modifier = Modifier.width(size)
            )
            state.ingredientUiState.filter { it.isSelected }.forEach {
                Ingredients(items = it, size = size / 6)
            }
        }
    }
//    LaunchedEffect(pagerState.currentPage) {
//        onBreadSelected(pagerState.currentPage) // Call the onBreadSelected callback with the current page index
//    }

}

@Composable
fun PlateImage(modifier: Modifier = Modifier) {
    CustomImage(
        painter = painterResource(id = R.drawable.plate),
        modifier = modifier
    )
}

@Composable
fun LargeCardPizza(
    state: HomeUiState,
    onSizeSelected: (TypeSize) -> Unit,
) {
    CardPizzaSize(text = "L",
        state = state,
        typeSize = TypeSize.Large,
        onSizeSelected = { onSizeSelected(it) }
    )
}

@Composable
fun MediumCardPizza(
    state: HomeUiState,
    onSizeSelected: (TypeSize) -> Unit,
) {
    CardPizzaSize(
        text = "M",
        state = state,
        typeSize = TypeSize.Medium,
        onSizeSelected = { onSizeSelected(it) }
    )
}

@Composable
fun SmallCardPizza(
    state: HomeUiState,
    onSizeSelected: (TypeSize) -> Unit,
) {
    CardPizzaSize(
        text = "S",
        state = state,
        typeSize = TypeSize.Small,
        onSizeSelected = { onSizeSelected(it) })
}

@Composable
fun ItemPizzaIcon(onClickIngredient: (IngredientUiState) -> Unit, state: IngredientUiState) {
    val backgroundColor by animateColorAsState(if (state.isSelected) PaleGreen else Color.White)
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(color = backgroundColor)
            .clickable { onClickIngredient(state) }
    ) {
        Image(
            painter = painterResource(id = state.imageIngredients),
            contentDescription = null,
            modifier = Modifier.width(35.dp)
        )
    }
}

//@Composable
//fun CardPizzaSize(
//    state: HomeUiState,
//    text: String,
//    modifier: Modifier = Modifier,
//    typeSize: TypeSize,
//    onSizeSelected: (TypeSize) -> Unit
//) {
//    val elevation by animateDpAsState(if (state.selectedSizePizza == typeSize) 16.dp else 0.dp)
//
//    Card(
//        modifier = modifier
//            .size(45.dp)
//            .background(Color.White, CircleShape)
//            .shadow(elevation = elevation,CircleShape),
//        shape = CircleShape,
//        colors = CardDefaults.cardColors(Color.White),
////        elevation = CardDefaults.cardElevation(elevation)
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .clickable { onSizeSelected(typeSize) },
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                text = text,
//                textAlign = TextAlign.Center
//            )
//        }
//    }
//}
@Composable
fun CardPizzaSize(
    state: HomeUiState,
    text: String,
    modifier: Modifier = Modifier,
    typeSize: TypeSize,
    onSizeSelected: (TypeSize) -> Unit
) {
    val elevation by animateDpAsState(if (state.selectedSizePizza == typeSize) 16.dp else 0.dp)
    AnimatedVisibility(visible = state.selectedSizePizza == typeSize, enter = slideInHorizontally(), exit = slideOutHorizontally() ) {

    }
    Card(
        modifier = modifier
            .size(45.dp)
            .background(Color.White, CircleShape)
            .shadow(elevation = elevation, CircleShape),
        shape = CircleShape,
        colors = CardDefaults.cardColors(Color.White),
//        elevation = CardDefaults.cardElevation(elevation)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onSizeSelected(typeSize) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Ingredients(items: IngredientUiState, size: Dp) {
    FlowRow(
        maxItemsInEachRow = 3,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items.ingredients.forEach {
            Image(
                modifier = Modifier
                    .width(size)
                    .clip(CircleShape),
                painter = painterResource(id = it),
                contentDescription = null,
                contentScale = ContentScale.Inside
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun MyPreview() {
//    Ingredients(items = basil, size = 165.dp)
}
