package com.vald3nir.shoppinglist.repository

import android.content.Context
import com.vald3nir.shoppinglist.db.dao.ProductsDao
import com.vald3nir.shoppinglist.domain.dto.ProductDTO
import com.vald3nir.shoppinglist.domain.dto.ProductFromBarcodeDTO
import com.vald3nir.shoppinglist.domain.mapper.toModel
import com.vald3nir.shoppinglist.repository.api.PublicServicesAPI
import com.vald3nir.shoppinglist.repository.usecases.FirebaseUseCase
import com.vald3nir.tolkit.networking.rest.RestClientExecutor
import javax.inject.Inject

interface ProductsRepository {
    suspend fun hasSavedProducts(): Boolean
    suspend fun insertProducts(products: List<ProductDTO>)
    suspend fun insertProductsFromDataset(context: Context)
    fun loadProductsFromFirebase(onSuccess: (List<ProductDTO>) -> Unit, onError: ((Exception?) -> Unit)?)
    suspend fun findProductFromBarcode(barcode: String): ProductFromBarcodeDTO?
}

class ProductsRepositoryImpl @Inject constructor(
    private val productsDao: ProductsDao,
    private val publicServicesAPI: PublicServicesAPI,
) : ProductsRepository, RestClientExecutor() {

    override suspend fun hasSavedProducts() = !productsDao.isEmpty()

    override suspend fun insertProductsFromDataset(context: Context) {
        productsDao.clearAndInsert(context.datasetsToProducts().toModel())
    }

    override suspend fun insertProducts(products: List<ProductDTO>) {
        productsDao.clearAndInsert(products.toModel())
    }

    override fun loadProductsFromFirebase(onSuccess: (List<ProductDTO>) -> Unit, onError: ((Exception?) -> Unit)?) {
        FirebaseUseCase.loadProductsFromFirebase(onSuccess, onError)
    }

    override suspend fun findProductFromBarcode(barcode: String) = execute {
        publicServicesAPI.getProductByBarcode(barcode)
    }
}

private fun Context.datasetsToProducts(): List<ProductDTO> {
    val products = mutableListOf<ProductDTO>()
    runCatching {
        assets.open("products.csv").bufferedReader().useLines { lines ->
            lines.drop(1).forEach { line -> // Ignore the header line
                val fields = line.split(",")
                if (fields.size >= 2) {
                    products.add(ProductDTO(name = fields[0].trim(), category = fields[1].trim()))
                }
            }
        }
    }
    return products
}