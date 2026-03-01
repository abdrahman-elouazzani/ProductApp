
import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("product_id")
    val productId: Long,
    @SerializedName("product_name")
    val productName: String,
    val description: String,
    val price: Double,
    @SerializedName("images_url")
    val imagesUrl: ImagesUrlDto,
    @SerializedName("c_brand")
    val brand: BrandDto
)

data class ImagesUrlDto(
    val small: String,
    val large: String
)

data class BrandDto(
    val id: String,
    val name: String
)