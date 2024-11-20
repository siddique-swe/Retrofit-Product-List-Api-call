package com.example.retrofittutorial.data.model

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("products/category/smartphones")
    suspend fun  getProductsList() : Product

    companion object{
        const val BASE_URL = "https://dummyjson.com/"
    }
}
//@GET("prroducts")
//suspend fun  getProductsList(
   // @Path("type") type : String,
  //  @Query("page") page :Int,
  //  @Query("api_key") apiKey :String
//) : Product