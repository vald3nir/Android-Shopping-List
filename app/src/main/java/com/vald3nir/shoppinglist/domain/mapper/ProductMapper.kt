package com.vald3nir.shoppinglist.domain.mapper

import com.vald3nir.shoppinglist.domain.dto.ProductDTO
import com.vald3nir.shoppinglist.repository.db.model.entities.ProductModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun ProductDTO.toModel() = ProductModel(
    id = id,
    name = name,
    category = category
)

fun List<ProductDTO>.toModel() = this.map { it.toModel() }

fun ProductModel.toDTO() = ProductDTO(
    id = id,
    name = name,
    category = category
)

fun List<ProductModel>.toDTO() = this.map { it.toDTO() }

fun Flow<List<ProductModel>>.toDTO(): Flow<List<ProductDTO>> = map { it.toDTO() }