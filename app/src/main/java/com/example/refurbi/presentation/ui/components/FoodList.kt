package com.example.refurbi.presentation.ui.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import flexi_store.composeapp.generated.resources.Res
import flexi_store.composeapp.generated.resources.Roboto_Bold
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.refurbi.app.domain.model.products.Products
import com.example.refurbi.presentation.ui.screens.detail.all.SeeAllProducts
import com.example.refurbi.presentation.ui.screens.detail.common.DetailScreen
import com.example.refurbi.theme.LocalThemeIsDark
import org.refurbi.app.utils.Constant
import org.refurbi.app.utils.generateRandomColor
import org.jetbrains.compose.resources.Font

@Composable
fun FoodList(products: List<Products>) {
    val isDark by LocalThemeIsDark.current
    val navigator = LocalNavigator.current
    val filteredList = products.filter { it.categoryId == 15 }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Food & Groceries",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDark) Color.White else Color.Black,
                modifier = Modifier.padding(start = 10.dp)
            )
            Text(
                text = "See All",
                fontSize = 14.sp,
                color = Color(0xFFe85110),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        navigator?.push(
                            SeeAllProducts(
                                filteredList,
                                books = null,
                                "Food & Groceries"
                            )
                        )
                    }
                    .padding(end = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(filteredList) { product ->
                FoodItems(product)
            }
        }
    }
}


@Composable
fun FoodItems(
    products: Products,
) {
    val randomColor = remember { generateRandomColor() }
    val navigator = LocalNavigator.current
    val image: Resource<Painter> = asyncPainterResource(Constant.BASE_URL + products.imageUrl)
    Card(
        modifier = Modifier.width(220.dp)
            .height(180.dp)
            .clickable {
                navigator?.push(DetailScreen(products))
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(
            width = 1.dp,
            color = Color.LightGray
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth().clickable {
            navigator?.push(DetailScreen(products))
        }) {
            KamelImage(
                resource = image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)),
                contentScale = ContentScale.Crop,
                animationSpec = tween(
                    durationMillis = 200,
                    delayMillis = 100,
                    easing = LinearOutSlowInEasing
                ),
                onFailure = {
                    Text("Failed to Load")
                }
            )
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(top = 6.dp, end = 8.dp)
                    .size(25.dp)
                    .clip(CircleShape)
                    .background(
                        Color.DarkGray.copy(alpha = 0.65f)
                    )
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
            )
        }
        Column(
            modifier = Modifier.fillMaxSize()
                .background(randomColor)
                .padding(all = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = products.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    fontFamily = FontFamily(Font(Res.font.Roboto_Bold)),
                    modifier = Modifier.fillMaxWidth()
                        .weight(1f),
                )
                Text(
                    text = "$" + products.price,
                    modifier = Modifier.padding(start = 10.dp),
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(Res.font.Roboto_Bold)),
                    color = Color.White
                )
            }
        }
    }
}