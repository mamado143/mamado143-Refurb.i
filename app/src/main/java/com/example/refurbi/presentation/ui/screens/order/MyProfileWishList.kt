package com.example.refurbi.presentation.ui.screens.order

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.refurbi.app.domain.model.products.Products
import org.refurbi.app.domain.usecase.ResultState
import com.example.refurbi.presentation.ui.components.FavouriteList
import com.example.refurbi.presentation.ui.screens.favourite.OptionText
import com.example.refurbi.presentation.viewmodels.MainViewModel
import com.example.refurbi.theme.LocalThemeIsDark
import org.koin.compose.koinInject

class MyProfileWishList : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val isDark by LocalThemeIsDark.current
        val viewModel: MainViewModel = koinInject()
        val navigator = LocalNavigator.current
        var productList by remember { mutableStateOf<List<Products>>(emptyList()) }
        var filteredProductList by remember { mutableStateOf<List<Products>>(emptyList()) }
        var searchQuery by remember { mutableStateOf("") }
        var selectedOption by remember { mutableStateOf("Latest") }
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
            rememberTopAppBarState()
        )

        val state = rememberLazyGridState()
        val options = listOf(
            "Latest",
            "Most Popular",
            "Cheap",
            "Most Expensive",
            "Top Rated",
            "Trending",
            "Limited Edition",
            "Best Sellers",
            "Exclusive Deals"
        )

        LaunchedEffect(Unit) {
            viewModel.getProducts()
        }
        val productState by viewModel.products.collectAsState()

        if (productList.isEmpty()) {
            productState?.let {
                productList = when (it) {
                    is ResultState.Success -> it.response
                    else -> emptyList()
                }
                filteredProductList = productList
            }
        }

        LaunchedEffect(productState, searchQuery) {
            val response = when (val state = productState) {
                is ResultState.Success -> state.response
                else -> emptyList()
            }
            productList = response
            filteredProductList = filterProductList(productList, selectedOption, searchQuery)
        }

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "My Favourite",
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    actions = {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = null,
                            tint = if (isDark) Color.White else Color.Black
                        )
                    },
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBackIosNew,
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                                .clickable {
                                    navigator?.pop()
                                },
                            tint = if (isDark) Color.White else Color.Black
                        )
                    },
                    scrollBehavior = scrollBehavior
                )
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .background(color = if (isDark) MaterialTheme.colorScheme.background else Color.White)
                    .padding(
                        top = it.calculateTopPadding(),
                        start = 4.dp,
                        end = 4.dp
                    )
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = { search ->
                        searchQuery = search
                    },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null,
                        )
                    },
                    placeholder = {
                        Text("Search Something")
                    },
                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp)
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        unfocusedPlaceholderColor = Color.LightGray,
                        unfocusedLeadingIconColor = Color.Black,
                        focusedLeadingIconColor = Color.Black,
                        unfocusedTrailingIconColor = Color.Black,
                        focusedPlaceholderColor = Color.Gray,
                        focusedTrailingIconColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        filteredProductList =
                            filterProductList(productList, selectedOption, searchQuery)
                    })
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(options) { op ->
                        OptionText(text = op, isSelected = op == selectedOption) {
                            selectedOption = op
                            filteredProductList =
                                filterProductList(productList, selectedOption, searchQuery)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                FavouriteList(products = filteredProductList, state = state)
            }
        }
    }

    private fun filterProductList(
        products: List<Products>,
        selectedOption: String,
        searchQuery: String,
    ): List<Products> {
        val filteredByName = products.filter { it.name.contains(searchQuery, ignoreCase = true) }
        val filteredByCategory =
            products.filter { it.categoryTitle.contains(searchQuery, ignoreCase = true) }
        val filteredList = (filteredByName + filteredByCategory).distinct()

        return when (selectedOption) {
            "Latest" -> filteredList.sortedByDescending { it.createdAt }
            "Most Popular" -> filteredList.sortedByDescending { it.averageRating }
            "Cheap" -> filteredList.sortedBy { it.price }
            "Most Expensive" -> filteredList.sortedByDescending { it.price }
            "Top Rated" -> filteredList.sortedByDescending { it.averageRating }
            "Trending" -> filteredList.sortedByDescending { it.isFeatured }
            "Limited Edition" -> filteredList.filter { it.isAvailable }
                .sortedByDescending { it.totalStack }

            "Best Sellers" -> filteredList.sortedWith(compareByDescending<Products> { it.averageRating }.thenBy { it.discountPrice })
            "Exclusive Deals" -> filteredList.filter { it.isFeatured }.sortedBy { it.discountPrice }
            else -> filteredList
        }
    }
}