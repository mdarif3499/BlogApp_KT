package com.example.userloginkotlinapplication.service.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.userloginkotlinapplication.service.model.Book
import com.example.userloginkotlinapplication.service.model.Erroess
import com.example.userloginkotlinapplication.service.model.User
import com.example.userloginkotlinapplication.service.model.UserPostModel1
import com.example.userloginkotlinapplication.service.network.Apiservice
import com.example.userloginkotlinapplication.service.network.BookDatabase
import com.example.userloginkotlinapplication.utils.MySharedpreference
import com.example.userloginkotlinapplication.utils.MySharedpreferenceKey
import com.example.userloginkotlinapplication.utils.NetworkUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserPostRepository(val apiService: Apiservice, val applicationContext: Context) {

    private val liveData = MutableLiveData<List<UserPostModel1>>()


    val db = Room.databaseBuilder(
        applicationContext,
        BookDatabase::class.java, "book_database"
    ).fallbackToDestructiveMigration().build()
    val bookDao = db.bookDao()


    val userList: LiveData<List<UserPostModel1>>
        get() = liveData

    suspend fun getUserPost() {
        val mySharedPreferences = MySharedpreference(applicationContext)
        val token = mySharedPreferences.getString(MySharedpreferenceKey.TOKEN)

        if (NetworkUtils.isInternetAvailable(applicationContext)) {

            val result = apiService.getUserPost("Bearer " + token)
            if (result.isSuccessful) {
                val postList = result.body()


                testDB(postList!!.data)




                liveData.postValue(postList?.data)


            } else {

                MySharedpreference(applicationContext).setboolean(
                    MySharedpreferenceKey.ISCHECK,
                    false
                )
                val gson = Gson()
                val type = object : TypeToken<Erroess>() {}.type
                var errorResponse: Erroess? = gson.fromJson(result.errorBody()!!.charStream(), type)
                val s = errorResponse?.message
                var newCat: MutableList<UserPostModel1> = arrayListOf()





                liveData.postValue(newCat)


            }


        } else {
            val gson = Gson()
            val bookss = bookDao.getAllBooks()
            var postList: MutableList<UserPostModel1> = arrayListOf()


            for (i in bookss.indices) {

                val item = bookss.get(i)

                var userDetails = gson.fromJson(item.user, User::class.java)

                val categoryarray = gson.fromJson(item.category, Array<String>::class.java).toList()

                var postList1 = UserPostModel1(
                    item._id,
                    item.title,
                    item.body,
                    item.photo,
                    item.userId,
                    "",
                    "",
                    categoryarray,
                    userDetails
                )

                postList.add(postList1)
            }
            liveData.postValue(postList)

        }


    }


    private suspend fun testDB(post: List<UserPostModel1>) {

        bookDao.deleteBook()
        //Insert
        for (item in post) {

            val gson = Gson()
            val category: String = gson.toJson(item.category)
            val user: String = gson.toJson(item.user)


            bookDao.insertBook(
                Book(
                    0,
                    item._id,
                    item.title,
                    item.body,
                    item.userId,
                    item.photo,
                    category,
                    user
                )
            )


        }

    }


}