package com.example.userloginkotlinapplication.service.network

import com.example.userloginkotlinapplication.service.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Apiservice {

    @POST("api/auth/login")
    suspend fun loginUser(@Query("userName")  name:String, @Query("password")password: String ): Response<LoginModeldata>

    @GET("/api/postBlog")
    suspend fun getUserPost(@Header("Authorization") authToken: String?): Response<Root>

    @POST("/api/auth/signup")
    suspend   fun signUp(@Body user: Usere?):Response<SignUpSuccessfull>

    @GET("/api/postBlog")
    suspend  fun getMyPost(
        @Header("Authorization") authToken: String?,
        @Query("userId") userId: String?
    ): Response<MyPostModel>

    @PUT("/api/users/{id}")
    suspend  fun updateData(
        @Path("id") id: String?,
        @Header("Authorization") authToken: String?,
        @Body user: Usere?
    ): Response<SignUpSuccessfull>


    @POST("/api/postBlog")
    suspend   fun addMyPost(@Header("Authorization") authToken: String?, @Body user: MyPost?): Response<GetPostAddSuccess>

    @GET("/api/category")
    suspend fun getCategory(@Header("Authorization") authToken: String?): Response<CategoryModel>

    @PUT("/api/postBlog/{id}")
    suspend fun updateUserPost(
        @Path("id") id: String?,
        @Header("Authorization") authToken: String?,
        @Body user: MutableMap<String, Any>?
    ): Response<SignUpSuccessfull>


    @DELETE("/api/postBlog/{id}")
    suspend  fun deletePost(
        @Path("id") id: String?,
        @Header("Authorization") authToken: String?
    ): Response<SignUpSuccessfull>









}