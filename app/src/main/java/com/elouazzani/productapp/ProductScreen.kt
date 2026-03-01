package com.elouazzani.productapp

import ProductViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.elouazzani.domain.model.Product
import com.elouazzani.domain.model.Review
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier,
        topBar = {
            TextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    viewModel.onSearch(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = { Text("Search products...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = viewModel::toggleSort) {
                Icon(Icons.Default.List, contentDescription = "Sort Reviews")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(androidx.compose.ui.Alignment.Center))
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.products) { product ->
                    ProductItem(
                        product = product,
                        isDescending = state.isDescending
                    )
                }
            }

            if (state.error.isNotEmpty()) {
                Text(text = state.error, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, isDescending: Boolean) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = product.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "${product.price} €", style = MaterialTheme.typography.bodyMedium)

            // Expandable Review Section
            TextButton(onClick = { expanded = !expanded }) {
                Text("Reviews (Click to ${if (expanded) "Hide" else "Show"})")
            }

            if (expanded) {
                // Fetch and display reviews here using a local state or
                product.reviews?.let {
                    ReviewList(reviews = it, isDescending = isDescending)
                }
            }
        }
    }
}

@Composable
fun ReviewList(reviews: List<Review>, isDescending: Boolean) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        reviews.sortedBy { it.rating }.let { sortedReviews ->
            if (isDescending) {
                sortedReviews.reversed()
            } else {
                sortedReviews
            }.forEach { review ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)

                ) {
                    Text(
                        text = review.text.orEmpty(),
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = review.rating.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFFFFC107)
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        tint = Color(0xFFFFC107),
                        contentDescription = null
                    )
                }
            }
        }

    }
}