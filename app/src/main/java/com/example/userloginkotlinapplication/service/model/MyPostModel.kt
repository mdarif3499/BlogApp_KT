package com.example.userloginkotlinapplication.service.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


data class MyPostModel(
    val message: String,
    val data: List<UserPostModel1>,
)


data class UserPostModel1(
    var _id: String,
    var title: String,
    var body: String,
    var photo: String,
    var userId: String,
    val createdAt: String,
    val updatedAt: String,
    val category: List<String>,
    val user: User
)

//object Converters {
//    @TypeConverter
//    fun fromString(value: String?): ArrayList<String> {
//        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
//        return Gson().fromJson(value, listType)
//    }
//
//    @TypeConverter
//    fun fromArrayList(list: ArrayList<String?>?): String {
//        val gson = Gson()
//        return gson.toJson(list)
//    }
//}


data class User(
    val _id: String,
    val name: String,
    val userName: String,
    val email: String,
    val password: String,
    val profile: String,
    val createdAt: String,
    val updatedAt: String,


    )


