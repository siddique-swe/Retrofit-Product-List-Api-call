package com.example.retrofittutorial.data.model

import coil.network.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException

class ProductRepoImplmntion(
    private val api : Api
) : ProductRepository{
    override suspend fun getProductsList(): Flow<Result<List<ProductX>>> {
       return flow {
           val productFromApi = try {
                api.getProductsList()
           } catch (e: IOException){
                e.printStackTrace()
               emit(Result.Error(message = "error loading products"))
               return@flow
           }catch (e: HttpException){
               e.printStackTrace()
               emit(Result.Error(message = "error loading products"))
               return@flow
           }catch (e: Exception){
               e.printStackTrace()
               emit(Result.Error(message = "error loading products"))
               return@flow
           }
           emit(Result.Success(productFromApi.products))
       }
    }

}