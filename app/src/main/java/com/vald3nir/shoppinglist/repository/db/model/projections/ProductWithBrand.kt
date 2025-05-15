package com.vald3nir.shoppinglist.repository.db.model.projections

import androidx.room.Embedded
import androidx.room.Relation
import com.vald3nir.shoppinglist.repository.db.model.entities.ProductBrandsModel
import com.vald3nir.shoppinglist.repository.db.model.entities.ProductModel

data class ProductWithBrand(
    @Embedded val product: ProductModel?,
    @Relation(
        parentColumn = "brandId",
        entityColumn = "id"
    )
    val brand: ProductBrandsModel?
)
