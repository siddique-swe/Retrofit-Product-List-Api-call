package com.example.retrofittutorial.data.model

import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProductsList(): Flow<Result<List<ProductX>>>
}