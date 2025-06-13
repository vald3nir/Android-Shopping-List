package com.vald3nir.shoppinglist.domain.mapper

import com.vald3nir.shoppinglist.db.model.entities.ProductModel
import com.vald3nir.shoppinglist.domain.dto.ProductDTO
import com.vald3nir.toolkit.helpers.utils.parses.fromJsonToObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun ProductDTO.toModel() = ProductModel(
    id = id,
    name = name,
    category = category
)

fun ProductModel.toDTO() = ProductDTO(
    id = id,
    name = name,
    category = category
)

fun List<String?>.toProductsModel(): List<ProductModel> {
    val list = mutableListOf<ProductModel>()
    this.forEach { dataJson ->
        if (!dataJson.isNullOrEmpty()) {
            list.add(fromJsonToObject<ProductModel>(dataJson))
        }
    }
    return list
}

fun List<ProductDTO>.toModel() = this.map { it.toModel() }

fun List<ProductModel>.toDTO() = this.map { it.toDTO() }

fun Flow<List<ProductModel>>.toDTO(): Flow<List<ProductDTO>> = map { it.toDTO() }