import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elouazzani.core.utils.Results
import com.elouazzani.domain.model.Product
import com.elouazzani.domain.usecases.GetProductsReviewUseCase
import com.elouazzani.domain.usecases.GetProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// In :app module
class ProductViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val getReviewsUseCase: GetProductsReviewUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState

    private var allProducts = emptyList<Product>()


    init {
        fetchProducts()
    }


    fun fetchProducts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            getProductsUseCase().collect { result ->
                when (result) {
                    is Results.Success -> {
                        getReviewsUseCase().collect { reviewsResult ->
                            when (reviewsResult) {
                                is Results.Success -> {
                                    val allProductsReviews = reviewsResult.data ?: emptyList()
                                    allProducts = (result.data ?: emptyList()).map { product ->
                                        product.copy(
                                            reviews = allProductsReviews.find { it.productId == product.id }?.reviews
                                        )
                                    }
                                    _uiState.value = _uiState.value.copy(products = allProducts)
                                }

                                is Results.Error -> {
                                    _uiState.value =
                                        _uiState.value.copy(error = result.message ?: "Unknown error")
                                }
                            }
                        }

                    }

                    is Results.Error -> {
                        _uiState.value =
                            _uiState.value.copy(error = result.message ?: "Unknown error")
                    }
                }
            }
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }



    fun onSearch(query: String) {
        if (query.isNotEmpty()) {
            _uiState.value = _uiState.value.copy(products = allProducts.filter { product ->
                product.name.contains(query, ignoreCase = true)
            })
        } else {
            _uiState.value = _uiState.value.copy(products = allProducts)
        }
    }

    fun toggleSort() {
        _uiState.value = _uiState.value.copy(isDescending = !_uiState.value.isDescending)
    }
}

data class ProductUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String = "",
    val isDescending: Boolean = true
)