package org.refurbi.app.data.local

import org.refurbi.app.db.MyDatabase
import org.refurbi.app.db.ProductEntity

class DataSourceImpl(
    private val db: MyDatabase,
) : DataSource {
    override suspend fun getProductById(id: Int): ProductEntity? {
        TODO("Not yet implemented")
    }

    override fun getAllProducts(): List<ProductEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProductById(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun insertProduct(
        id: Int?,
        averageRating: Double,
        brand: String,
        categoryId: Int,
        categoryTitle: String,
        createdAt: String,
        description: String,
        dimensions: String,
        discountPrice: Int,
        imageUrl: String,
        isAvailable: Boolean,
        name: String,
        price: Int,
        promotionDescription: String,
        totalStack: Int,
        updatedAt: String,
        weight: Double,
        isFeatured: Boolean,
        manufacturer: String,
        colors: String,
    ) {
        TODO("Not yet implemented")
    }
}