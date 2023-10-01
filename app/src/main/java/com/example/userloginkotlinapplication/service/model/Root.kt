package com.example.userloginkotlinapplication.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

data class Root(
    val message: String,
    val data: List<UserPostModel1>,
)


data class Usere(
    val name: String,
    val userName: String,
    val email: String,
    val password: String,
)

@Entity(tableName = "books_table")
data class Book(

    @PrimaryKey(autoGenerate = true) var id: Int,

    @ColumnInfo(name = "_id")
    var _id: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "body")
    var body: String,

    @ColumnInfo(name = "userId")
    var userId: String,

    @ColumnInfo(name = "photo")
    var photo: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "User")
    val user: String
)


class Converters {
    @TypeConverter
    fun fromString(value: String?): ArrayList<String> {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
